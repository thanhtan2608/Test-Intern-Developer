package org.example.ql_voucher_be.infrastructure.persistence.Users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages.VoucherUsageDb;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users") // Ánh xạ tới bảng users trong DB
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Tự động gán thời gian khi tạo mới nếu DB không gán mặc định
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VoucherUsageDb> voucherUsages;
}