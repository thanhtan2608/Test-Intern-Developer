package org.example.ql_voucher_be.app.usecase;

import org.example.ql_voucher_be.app.dto.input.UsersRequest;
import org.example.ql_voucher_be.app.dto.output.UsersRespone;

public interface ICreateUsers {
    UsersRespone execute(UsersRequest request);
}
