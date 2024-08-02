package com.movie.ticket.booking.system.payment.service_payment.service.dto;


import com.movie.ticket.booking.system.payment.service_payment.service.enums.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class BookingDTO {

    private UUID bookingId;

    private String userId;

    private Integer movieId;

    private List<String> seatsSelected;

    private LocalDate showDate;

    private LocalTime showTime;

    private Double bookingAmount;

    private BookingStatus bookingStatus;
}
