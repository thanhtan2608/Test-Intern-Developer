package org.example.ql_voucher_be.app.mapper;

import org.example.ql_voucher_be.app.dto.output.VouchersRespone;
import org.example.ql_voucher_be.domain.entity.Vouchers;
import org.example.ql_voucher_be.infrastructure.persistence.Vouchers.VouchersDb;

public class VoucherMapper {
    public static Vouchers toDomain(VouchersDb db) {
        return Vouchers.builder()
                .id(db.getId())
                .code(db.getCode())
                .discount_percent(db.getDiscountPercent())
                .quantity(db.getQuantity())
                .expired_date(db.getExpiredDate())
                .status(db.getStatus())
                .created_at(db.getCreatedAt())
                .build();
    }

    public static VouchersDb toDb(Vouchers domain) {
        VouchersDb db = new VouchersDb();
        db.setId(domain.getId());
        db.setCode(domain.getCode());
        db.setDiscountPercent(domain.getDiscount_percent());
        db.setQuantity(domain.getQuantity());
        db.setExpiredDate(domain.getExpired_date());
        db.setStatus(domain.getStatus());
        db.setCreatedAt(domain.getCreated_at());
        return db;
    }
    public static VouchersRespone toResponse(Vouchers domain) {
        if (domain == null) return null;
        return new VouchersRespone(
                domain.getId(),
                domain.getCode(),
                domain.getDiscount_percent(),
                domain.getQuantity(),
                domain.getExpired_date(),
                domain.getStatus(),
                domain.getCreated_at()
        );
    }
}
