package org.example.ql_voucher_be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voucher_usages {
    private Long id;
    private Long user_id;
    private Long voucher_id;
   private Date used_at;
}
