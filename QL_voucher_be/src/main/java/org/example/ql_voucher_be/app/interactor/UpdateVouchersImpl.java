package org.example.ql_voucher_be.app.interactor;


import org.example.ql_voucher_be.app.dto.input.VouchersRequest;
import org.example.ql_voucher_be.app.dto.output.VouchersRespone;
import org.example.ql_voucher_be.app.mapper.VoucherMapper;
import org.example.ql_voucher_be.app.usecase.IUpdateVoucher;
import org.example.ql_voucher_be.domain.entity.Vouchers;
import org.example.ql_voucher_be.domain.exception.DomainException;
import org.example.ql_voucher_be.domain.repository.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateVouchersImpl implements IUpdateVoucher {

    @Autowired
    private VouchersRepository vouchersRepository;

    @Override
    public VouchersRespone execute(Long id, VouchersRequest request) {
        Vouchers existing = vouchersRepository.findById(id)
                .orElseThrow(() -> new DomainException("Cập nhật thất bại: Không tìm thấy voucher"));

        // Cập nhật dữ liệu mới
        existing.setCode(request.code());
        existing.setDiscount_percent(request.discountPercent());
        existing.setQuantity(request.quantity());
        existing.setExpired_date(request.expiredDate());
        existing.setStatus(request.status());

        existing.validateVoucher(); // Đảm bảo dữ liệu mới vẫn hợp lệ

        Vouchers updated = vouchersRepository.save(existing);
        return VoucherMapper.toResponse(updated);
    }
}