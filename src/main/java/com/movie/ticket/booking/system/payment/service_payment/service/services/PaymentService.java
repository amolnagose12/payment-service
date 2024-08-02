package com.movie.ticket.booking.system.payment.service_payment.service.services;

import com.movie.ticket.booking.system.payment.service_payment.service.dto.BookingDTO;

public interface PaymentService {

    public BookingDTO makePayment(BookingDTO bookingDTO);
}
