package org.example.ql_voucher_be.app.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record VouchersRespone(
        Long id,
        String code,
        int discountPercent,
        int quantity,
        LocalDate expiredDate,
        String status,
        LocalDateTime createdAt
) {
}
