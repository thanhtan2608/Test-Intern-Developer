package org.example.ql_voucher_be.presentation.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.dto.input.VoucherUsageRequest;
import org.example.ql_voucher_be.app.dto.output.VoucherUsageRespone;
import org.example.ql_voucher_be.app.usecase.IGetVoucherHistory;
import org.example.ql_voucher_be.app.usecase.IUseVouchers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voucher-usages")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class VoucherUsageController {

    private final IUseVouchers useVouchers;
    private final IGetVoucherHistory getVoucherHistory;

    /**
     * API: POST /api/voucher-usages
     * Mô tả: User sử dụng voucher
     */
    @PostMapping
    public ResponseEntity<String> use(@Valid @RequestBody VoucherUsageRequest request) {
        useVouchers.execute(request.voucherCode(), request.userId());
        return new ResponseEntity<>("Sử dụng voucher thành công!", HttpStatus.CREATED);
    }

    /**
     * API: GET /api/voucher-usages
     * Mô tả: Xem lịch sử sử dụng
     */
    @GetMapping
    public ResponseEntity<List<VoucherUsageRespone>> getHistory() {
        // Gọi UseCase xử lý logic và nhận về danh sách DTO
        List<VoucherUsageRespone> history = getVoucherHistory.execute();

        // Trả về dữ liệu kèm mã HTTP 200 OK
        return ResponseEntity.ok(history);
    }
}