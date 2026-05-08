package org.example.ql_voucher_be.app.interactor;

import org.example.ql_voucher_be.app.usecase.IDeleteVouchers;
import org.example.ql_voucher_be.domain.exception.DomainException;
import org.example.ql_voucher_be.domain.repository.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteVouchersImpl implements IDeleteVouchers {

    @Autowired
    private VouchersRepository vouchersRepository;

    @Override
    public void execute(Long id) {
        if (!vouchersRepository.existsById(id)) {
            throw new DomainException("Không tìm thấy voucher để xóa");
        }
        vouchersRepository.deleteById(id);
    }
}