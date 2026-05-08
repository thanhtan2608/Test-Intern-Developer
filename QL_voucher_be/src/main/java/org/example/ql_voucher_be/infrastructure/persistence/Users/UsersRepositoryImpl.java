package org.example.ql_voucher_be.infrastructure.persistence.Users;

import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.domain.entity.Users;
import org.example.ql_voucher_be.domain.repository.UsersRepository;
import org.example.ql_voucher_be.app.mapper.UsersMapper; // Đảm bảo đúng package của Mapper
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {

    private final UsersJpaRepository usersJpaRepository;
    private final UsersMapper usersMapper; // Tiêm Mapper vào đây

    @Override
    public Users save(Users user) {
        // 1. Dùng mapper chuyển từ Domain Entity (Users) sang Db Entity (UsersDb)
        UsersDb dbEntity = usersMapper.toDbEntity(user);

        // 2. Lưu vào CSDL thông qua JpaRepository
        UsersDb savedDb = usersJpaRepository.save(dbEntity);

        // 3. Chuyển ngược từ Db Entity đã lưu sang Domain Entity để trả về
        return usersMapper.toDomainEntity(savedDb);
    }

    @Override
    public List<Users> findAll() {
        // Lấy danh sách từ DB và dùng mapper để chuyển đổi toàn bộ sang Domain Entity
        return usersJpaRepository.findAll().stream()
                .map(usersMapper::toDomainEntity)
                .collect(Collectors.toList());
    }
}