package org.example.ql_voucher_be.app.interactor;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.usecase.IUseVouchers;
import org.example.ql_voucher_be.domain.entity.Vouchers;
import org.example.ql_voucher_be.domain.repository.UsersRepository;
import org.example.ql_voucher_be.domain.repository.VouchersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UseVouchersImpl implements IUseVouchers {
    private final VouchersRepository voucherRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional // Rất quan trọng để đảm bảo tính toàn vẹn dữ liệu
    public void execute(String voucherCode, Long userId) {
        // 1. Tìm voucher và kiểm tra tồn tại
        Vouchers voucher = voucherRepository.findByCode(voucherCode)
                .orElseThrow(() -> new RuntimeException("Voucher không tồn tại!"));

        // 2. Kiểm tra các điều kiện: Còn lượt dùng? Còn hạn?
        if (voucher.getQuantity() <= 0) {
            throw new RuntimeException("Voucher đã hết lượt sử dụng!");
        }
        if (voucher.getExpired_date().isBefore(LocalDate.now())) {
            throw new RuntimeException("Voucher đã hết hạn!");
        }

        // 3. Thực hiện giảm số lượng (Quantity - 1)
        voucher.setQuantity(voucher.getQuantity() - 1);
        voucherRepository.save(voucher);

        // 4. Lưu lịch sử vào bảng voucher_usages
        voucherRepository.recordUsage(voucher.getId(), userId);
    }
}
