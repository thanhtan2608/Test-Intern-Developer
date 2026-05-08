package org.example.ql_voucher_be.infrastructure.persistence.Vouchers;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VoucherJpaRepository extends JpaRepository<VouchersDb, Long> {
    Optional<VouchersDb> findByCode(String code);
    boolean existsByCode(String code);
}