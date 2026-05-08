package org.example.ql_voucher_be.infrastructure.persistence.Vouchers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "vouchers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VouchersDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String code;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "expired_date", nullable = false)
    private LocalDate expiredDate;

    @Column(length = 20)
    private String status = "ACTIVE";

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Gán giá trị ngay trước khi lưu vào DB
    }
}