package org.example.ql_voucher_be.app.dto.output;

import java.time.LocalDateTime;

public record VoucherUsageRespone(
        Long id,
        String userName,
        String voucherCode,
        LocalDateTime usedAt
) {}