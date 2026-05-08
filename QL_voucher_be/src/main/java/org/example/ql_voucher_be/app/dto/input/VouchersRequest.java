package org.example.ql_voucher_be.app.dto.input;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record VouchersRequest(
        @NotBlank(message = "Mã voucher không được để trống")
        String code,
        @Min(value = 1, message = "Giảm giá tối thiểu là 1%")
        @Max(value = 100, message = "Giảm giá tối đa là 100%")
        int discountPercent,
        @Min(value = 0, message = "Số lượng không được âm")
        int quantity,
        @NotNull(message = "Ngày hết hạn không được để trống")
        @Future(message = "Ngày hết hạn phải lớn hơn ngày hiện tại")
        LocalDate expiredDate,
        String status
) {
}
