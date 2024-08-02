package com.movie.ticket.booking.system.payment.service_payment.service.entity;


import com.movie.ticket.booking.system.payment.service_payment.service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "booking_id")
    private UUID bookingId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "payment_date_and_time" )
    private LocalDateTime paymentDateTime;

}
