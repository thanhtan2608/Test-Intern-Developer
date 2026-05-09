package org.example.ql_voucher_be.domain.repository;

import org.example.ql_voucher_be.domain.entity.Vouchers;

import java.util.List;
import java.util.Optional;

public interface VouchersRepository {
    // Lưu mới hoặc cập nhật voucher
    Vouchers save(Vouchers voucher);

    // Lấy toàn bộ danh sách voucher
    List<Vouchers> findAll();

    // Tìm voucher theo ID (dùng cho cập nhật và xóa)
    Optional<Vouchers> findById(Long id);

    // Tìm voucher theo code (dùng cho chức năng Tìm kiếm và check trùng code)
    Optional<Vouchers> findByCode(String code);

    // Xóa voucher theo ID
    void deleteById(Long id);

    // Kiểm tra sự tồn tại của ID
    boolean existsById(Long id);
    void recordUsage(Long voucherId, Long userId);
}
