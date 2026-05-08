package org.example.ql_voucher_be.presentation.controller;

import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.dto.input.VouchersRequest;
import org.example.ql_voucher_be.app.dto.output.VouchersRespone;
import org.example.ql_voucher_be.app.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/vouchers")
public class VouchersController {
    private final ICreateVoucher createVoucher;
    private final IUpdateVoucher updateVoucher;
    private final IDeleteVouchers deleteVouchers;
    private final IGetAllVouchers getAllVouchers;
    private final ISearchVouchers searchVouchers;

    // 1. Thêm voucher mới
    @PostMapping
    public ResponseEntity<VouchersRespone> create(@RequestBody VouchersRequest request) {
        return new ResponseEntity<>(createVoucher.execute(request), HttpStatus.CREATED);
    }

    // 2. Cập nhật voucher
    @PutMapping("/{id}")
    public ResponseEntity<VouchersRespone> update(
            @PathVariable Long id,
            @RequestBody VouchersRequest request) {
        return ResponseEntity.ok(updateVoucher.execute(id, request));
    }

    // 3. Xóa voucher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteVouchers.execute(id);
        return ResponseEntity.noContent().build();
    }

    // 4. Xem danh sách voucher
    @GetMapping
    public ResponseEntity<List<VouchersRespone>> list() {
        return ResponseEntity.ok(getAllVouchers.execute());
    }

    // 5. Tìm kiếm voucher theo code
    @GetMapping("/search")
    public ResponseEntity<VouchersRespone> search(@RequestParam String code) {
        return ResponseEntity.ok(searchVouchers.execute(code));
    }
}
