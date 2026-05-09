package org.example.ql_voucher_be.app.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VoucherUsageRequest(
        @NotBlank(message = "Mã voucher không được để trống")
        String voucherCode,

        @NotNull(message = "ID người dùng không được để trống")
        Long userId
) {}