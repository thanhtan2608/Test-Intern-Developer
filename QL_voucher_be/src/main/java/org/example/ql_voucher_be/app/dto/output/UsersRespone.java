package org.example.ql_voucher_be.app.dto.output;

import java.time.LocalDateTime;

public record UsersRespone(
        Long id,
        String name,
        String email,
        String phone,
        LocalDateTime createdAt
) {

}
