package org.example.ql_voucher_be.app.usecase;

import org.example.ql_voucher_be.app.dto.output.VoucherUsageRespone;
import java.util.List;

public interface IGetVoucherHistory {
    List<VoucherUsageRespone> execute();
}