package org.example.ql_voucher_be.presentation.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.dto.input.UsersRequest;
import org.example.ql_voucher_be.app.dto.output.UsersRespone;
import org.example.ql_voucher_be.app.usecase.ICreateUsers;
import org.example.ql_voucher_be.app.usecase.IGetAllUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Prefix cho các API liên quan đến User
@AllArgsConstructor
@CrossOrigin(origins = "*") // Cho phép Frontend gọi API (CORS)
public class UsersController {

    private final IGetAllUsers getAllUsers;
    private final ICreateUsers createUsers;

    /**
     * API: GET /api/users
     * Mô tả: Lấy danh sách tất cả user
     */
    @GetMapping
    public ResponseEntity<List<UsersRespone>> getAll() {
        List<UsersRespone> response = getAllUsers.execute();
        return ResponseEntity.ok(response);
    }

    /**
     * API: POST /api/users
     * Mô tả: Tạo mới một user
     */
    @PostMapping
    public ResponseEntity<UsersRespone> create(@Valid @RequestBody UsersRequest request) {
        UsersRespone response = createUsers.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}