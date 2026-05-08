package org.example.ql_voucher_be.app.interactor;

import org.example.ql_voucher_be.app.dto.output.VouchersRespone;
import org.example.ql_voucher_be.app.mapper.VoucherMapper;
import org.example.ql_voucher_be.app.usecase.IGetAllVouchers;
import org.example.ql_voucher_be.domain.repository.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllVouchersImpl implements IGetAllVouchers {

    @Autowired
    private VouchersRepository vouchersRepository;

    @Override
    public List<VouchersRespone> execute() {
        return vouchersRepository.findAll().stream()
                .map(VoucherMapper::toResponse)
                .collect(Collectors.toList());
    }
}