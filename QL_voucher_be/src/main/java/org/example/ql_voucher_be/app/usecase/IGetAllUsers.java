package org.example.ql_voucher_be.app.usecase;

import org.example.ql_voucher_be.app.dto.output.UsersRespone;

import java.util.List;

public interface IGetAllUsers {
    List<UsersRespone> execute();
}
