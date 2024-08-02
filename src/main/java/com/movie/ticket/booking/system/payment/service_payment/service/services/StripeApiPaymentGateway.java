package com.movie.ticket.booking.system.payment.service_payment.service.services;

import com.movie.ticket.booking.system.payment.service_payment.service.dto.BookingDTO;
import com.movie.ticket.booking.system.payment.service_payment.service.enums.BookingStatus;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripeApiPaymentGateway {

    @Value("${stripe.key}")
    private String secreteKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = secreteKey;
    }

    public BookingDTO processPayment(BookingDTO bookingDTO){
        log.info("Entered into StripeApiPaymentGateway for doing the payment for booking id {}",
                bookingDTO.getBookingId());
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount",(int)Math.round(bookingDTO.getBookingAmount())*100);
        chargeParams.put("currency","INR");
        chargeParams.put("description", "Test Payment for booking service");
        chargeParams.put("source","tok_visa");

        try {
            Charge.create(chargeParams); //create online payment
            log.info("Payment Was successful for the booking id {}",bookingDTO.getBookingId());
            bookingDTO.setBookingStatus(BookingStatus.CONFIRMED);
        } catch (StripeException e) {
            log.error("error encounter during payment process : " + e.getMessage());
            bookingDTO.setBookingStatus(BookingStatus.CANCELLED);
        }
        return bookingDTO;
    }

}
