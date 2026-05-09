package org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages;

import jakarta.persistence.*;
import lombok.Data;
import org.example.ql_voucher_be.infrastructure.persistence.Users.UsersDb;
import org.example.ql_voucher_be.infrastructure.persistence.Vouchers.VouchersDb;

import java.time.LocalDateTime;

@Entity
@Table(name = "voucher_usages")
@Data
public class VoucherUsageDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private VouchersDb voucher;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersDb user;

    @Column(name = "used_at")
    private LocalDateTime usedAt = LocalDateTime.now();
}
