package org.example.ql_voucher_be.domain.repository;

import org.example.ql_voucher_be.domain.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    // Lưu hoặc cập nhật người dùng
    Users save(Users user);

    // Lấy danh sách tất cả người dùng
    List<Users> findAll();
    Optional<Users> findByEmail(String email);
}
