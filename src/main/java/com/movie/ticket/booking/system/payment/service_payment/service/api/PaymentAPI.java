package com.movie.ticket.booking.system.payment.service_payment.service.api;

import com.movie.ticket.booking.system.payment.service_payment.service.dto.BookingDTO;
import com.movie.ticket.booking.system.payment.service_payment.service.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
@Slf4j
public class PaymentAPI {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public BookingDTO makePayment(@RequestBody BookingDTO bookingDTO){
        log.info("Entered into payment API with the request {}", bookingDTO.toString());

        return this.paymentService.makePayment(bookingDTO);
    }
}
