package org.example.ql_voucher_be.app.dto.input;

import java.time.LocalDate;

public record VouchersRequest(
        String code,
        int discountPercent,
        int quantity,
        LocalDate expiredDate,
        String status
) {
}
