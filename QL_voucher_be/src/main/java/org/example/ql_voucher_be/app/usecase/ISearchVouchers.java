package org.example.ql_voucher_be.app.usecase;

import org.example.ql_voucher_be.app.dto.output.VouchersRespone;

public interface ISearchVouchers {
    VouchersRespone execute(String code);
}
