package org.example.ql_voucher_be.infrastructure.persistence.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersJpaRepository extends JpaRepository<UsersDb, Long> {
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh ở đây
    Optional<UsersDb> findByEmail(String email);
}