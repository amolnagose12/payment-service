package com.movie.ticket.booking.system.payment.service_payment.service.repository;

import com.movie.ticket.booking.system.payment.service_payment.service.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
