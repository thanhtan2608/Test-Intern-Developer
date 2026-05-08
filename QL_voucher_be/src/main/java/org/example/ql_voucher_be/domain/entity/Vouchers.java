package org.example.ql_voucher_be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ql_voucher_be.domain.exception.DomainException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vouchers {
    private Long id;
    private String code;
    private  Integer discount_percent;
    private  Integer quantity;
    private LocalDate expired_date;
    private String 	status;
    private LocalDateTime created_at;
    public void validateVoucher() {
        if (discount_percent < 1 || discount_percent > 100) {
            throw new DomainException("Discount percent must be between 1 and 100");
        }
        if (quantity < 0) {
            throw new DomainException("Quantity cannot be negative");
        }
    }
}
