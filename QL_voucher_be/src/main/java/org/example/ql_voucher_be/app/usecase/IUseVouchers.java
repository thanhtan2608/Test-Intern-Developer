package org.example.ql_voucher_be.app.usecase;

public interface IUseVouchers {
    void execute(String voucherCode, Long userId);
}
