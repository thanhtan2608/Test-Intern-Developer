package org.example.ql_voucher_be.app.usecase;

import org.example.ql_voucher_be.app.dto.input.VouchersRequest;
import org.example.ql_voucher_be.app.dto.output.VouchersRespone;

public interface IUpdateVoucher {
    VouchersRespone execute(Long id, VouchersRequest request);
}
