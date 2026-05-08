package org.example.ql_voucher_be.app.interactor;

import org.example.ql_voucher_be.app.dto.input.VouchersRequest;
import org.example.ql_voucher_be.app.dto.output.VouchersRespone;
import org.example.ql_voucher_be.app.mapper.VoucherMapper;
import org.example.ql_voucher_be.app.usecase.ICreateVoucher;
import org.example.ql_voucher_be.domain.entity.Vouchers;
import org.example.ql_voucher_be.domain.exception.DomainException;
import org.example.ql_voucher_be.domain.repository.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVouchersImpl implements ICreateVoucher {

    @Autowired
    private VouchersRepository vouchersRepository;

    @Override
    public VouchersRespone execute(VouchersRequest request) {
        // Kiểm tra trùng mã code
        if (vouchersRepository.findByCode(request.code()).isPresent()) {
            throw new DomainException("Mã voucher đã tồn tại trên hệ thống");
        }

        // Chuyển sang Domain Entity và validate logic lõi
        Vouchers voucher = Vouchers.builder()
                .code(request.code())
                .discount_percent(request.discountPercent())
                .quantity(request.quantity())
                .expired_date(request.expiredDate())
                .status("ACTIVE")
                .build();

        voucher.validateVoucher(); // Check 1-100%, quantity >= 0, date > now

        Vouchers saved = vouchersRepository.save(voucher);
        return VoucherMapper.toResponse(saved);
    }
}