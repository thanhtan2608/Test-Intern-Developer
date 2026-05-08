package org.example.ql_voucher_be.app.dto.input;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsersRequest(
        String name,
        @Email(message = "Email không đúng định dạng")
        @NotBlank(message = "Email không được để trống")
        String email,
        String phone
) {
}
