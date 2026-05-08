package org.example.ql_voucher_be.app.interactor;

import org.example.ql_voucher_be.app.dto.output.VouchersRespone;
import org.example.ql_voucher_be.app.mapper.VoucherMapper;
import org.example.ql_voucher_be.app.usecase.ISearchVouchers;
import org.example.ql_voucher_be.domain.entity.Vouchers;
import org.example.ql_voucher_be.domain.exception.DomainException;
import org.example.ql_voucher_be.domain.repository.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchVoucherImpl implements ISearchVouchers {

    @Autowired
    private VouchersRepository vouchersRepository;

    @Override
    public VouchersRespone execute(String code) {
        Vouchers voucher = vouchersRepository.findByCode(code)
                .orElseThrow(() -> new DomainException("Không tìm thấy mã voucher: " + code));

        return VoucherMapper.toResponse(voucher);
    }
}