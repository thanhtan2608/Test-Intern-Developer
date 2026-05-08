package org.example.ql_voucher_be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Date createdAt;
}
