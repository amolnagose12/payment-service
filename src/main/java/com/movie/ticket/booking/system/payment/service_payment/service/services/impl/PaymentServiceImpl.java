package com.movie.ticket.booking.system.payment.service_payment.service.services.impl;

import com.movie.ticket.booking.system.payment.service_payment.service.dto.BookingDTO;
import com.movie.ticket.booking.system.payment.service_payment.service.entity.PaymentEntity;
import com.movie.ticket.booking.system.payment.service_payment.service.enums.BookingStatus;
import com.movie.ticket.booking.system.payment.service_payment.service.enums.PaymentStatus;
import com.movie.ticket.booking.system.payment.service_payment.service.repository.PaymentRepository;
import com.movie.ticket.booking.system.payment.service_payment.service.services.PaymentService;
import com.movie.ticket.booking.system.payment.service_payment.service.services.StripeApiPaymentGateway;
import com.stripe.exception.StripeException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StripeApiPaymentGateway stripeApiPaymentGateway;

    @Override
    @Transactional(rollbackOn = StripeException.class)
    public BookingDTO makePayment(BookingDTO bookingDTO) {
        log.info("Entered into PaymentServiceImpl with request {}",bookingDTO.toString());

        PaymentEntity paymentEntity = PaymentEntity.builder()
                .bookingId(bookingDTO.getBookingId())
                .paymentStatus(PaymentStatus.PENDING)
                .paymentAmount(bookingDTO.getBookingAmount())
                .build();
        this.paymentRepository.save(paymentEntity);

        bookingDTO = this.stripeApiPaymentGateway.processPayment(bookingDTO);
        if (bookingDTO.getBookingStatus().equals(BookingStatus.CONFIRMED)){
            paymentEntity.setPaymentStatus(PaymentStatus.APPROVED);
            paymentEntity.setPaymentDateTime(LocalDateTime.now());
        }else {
            paymentEntity.setPaymentStatus(PaymentStatus.FAILED);
            paymentEntity.setPaymentDateTime(LocalDateTime.now());
        }
        return bookingDTO;

    }
}
