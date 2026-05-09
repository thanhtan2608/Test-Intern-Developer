package org.example.ql_voucher_be.domain.repository;

import org.example.ql_voucher_be.domain.entity.Voucher_usages;

import java.util.List;

public interface Voucher_usagesRepository {
    void saveUsage(Voucher_usages usage);
    List<Voucher_usages> findAll();
}
