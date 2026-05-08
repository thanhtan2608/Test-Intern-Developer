package org.example.ql_voucher_be.app.usecase;

import org.example.ql_voucher_be.app.dto.output.VouchersRespone;

import java.util.List;

public interface IGetAllVouchers {
    List<VouchersRespone> execute();
}