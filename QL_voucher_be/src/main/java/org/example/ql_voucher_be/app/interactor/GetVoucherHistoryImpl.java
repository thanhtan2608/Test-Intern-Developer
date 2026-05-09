package org.example.ql_voucher_be.app.interactor;

import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.dto.output.VoucherUsageRespone;
import org.example.ql_voucher_be.app.mapper.VouchersUsageMapper;
import org.example.ql_voucher_be.app.usecase.IGetVoucherHistory;
import org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages.VoucherUsageDb;
import org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages.VoucherUsageJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetVoucherHistoryImpl implements IGetVoucherHistory {
    private final VoucherUsageJpaRepository usageJpaRepository;
    private final VouchersUsageMapper usageMapper; // Inject Mapper vào

    @Override
    public List<VoucherUsageRespone> execute() {
        // 1. Lấy dữ liệu thô từ DB
        List<VoucherUsageDb> histories = usageJpaRepository.findAll();

        // 2. Dùng mapper để biến thành dữ liệu "đẹp" trả về cho Frontend
        return usageMapper.toResponseList(histories);
    }
}
