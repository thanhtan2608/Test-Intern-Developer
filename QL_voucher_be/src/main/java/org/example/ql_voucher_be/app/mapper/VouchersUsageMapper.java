package org.example.ql_voucher_be.app.mapper;

import org.example.ql_voucher_be.app.dto.output.VoucherUsageRespone;
import org.example.ql_voucher_be.domain.entity.Voucher_usages;
import org.example.ql_voucher_be.infrastructure.persistence.Users.UsersDb;
import org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages.VoucherUsageDb;
import org.example.ql_voucher_be.infrastructure.persistence.Vouchers.VouchersDb;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages.VoucherUsageDb;

@Component
public class VouchersUsageMapper {

    /**
     * Chuyển từ Persistence Entity (DB) sang DTO Response (trả về Frontend)
     */
    public VoucherUsageRespone toResponse(VoucherUsageDb dbEntity) {
        if (dbEntity == null) return null;

        return new VoucherUsageRespone(
                dbEntity.getId(),
                // Lấy tên từ quan hệ ManyToOne với UsersDb
                dbEntity.getUser() != null ? dbEntity.getUser().getFullName() : "Unknown User",
                // Lấy mã code từ quan hệ ManyToOne với VoucherDb
                dbEntity.getVoucher() != null ? dbEntity.getVoucher().getCode() : "Unknown Code",
                dbEntity.getUsedAt()
        );
    }

    /**
     * Chuyển đổi danh sách lịch sử
     */
    public List<VoucherUsageRespone> toResponseList(List<VoucherUsageDb> entities) {
        if (entities == null) return null;

        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    // Chuyển từ Domain sang DB để lưu
    public VoucherUsageDb toDbEntity(Voucher_usages domain) {
        if (domain == null) return null;
        VoucherUsageDb db = new VoucherUsageDb();

        // Gán các Object liên quan (Voucher và User)
        // Lưu ý: Tầng Infrastructure cần nhận ID từ Domain để map vào Entity DB
        VouchersDb vDb = new VouchersDb();
        vDb.setId(domain.getVoucher_id());

        UsersDb uDb = new UsersDb();
        uDb.setId(domain.getUser_id());

        db.setVoucher(vDb);
        db.setUser(uDb);
        db.setUsedAt(domain.getUsed_at());
        return db;
    }

    // Chuyển từ DB sang Domain để xử lý nghiệp vụ
    public Voucher_usages toDomainEntity(VoucherUsageDb db) {
        if (db == null) return null;
        return new Voucher_usages(
                db.getId(),
                db.getVoucher().getId(),
                db.getUser().getId(),
                db.getUsedAt()
        );
    }
}