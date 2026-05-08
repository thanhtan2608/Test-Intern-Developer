package org.example.ql_voucher_be.app.interactor;

import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.dto.input.UsersRequest;
import org.example.ql_voucher_be.app.dto.output.UsersRespone;
import org.example.ql_voucher_be.app.mapper.UsersMapper;
import org.example.ql_voucher_be.app.usecase.ICreateUsers;
import org.example.ql_voucher_be.domain.entity.Users;
import org.example.ql_voucher_be.domain.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateUsersImpl implements ICreateUsers {

    private final UsersRepository userRepository;
    private final UsersMapper usersMapper;

    @Override
    @Transactional
    public UsersRespone execute(UsersRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email này đã được sử dụng!");
        }
        // 1. Chuyển đổi DTO Request -> Entity
        Users userEntity = usersMapper.toEntity(request);

        // 2. Lưu vào CSDL
        // Lưu ý: createdAt sẽ được xử lý bởi @PrePersist trong lớp DB/Entity
        // hoặc tự động nếu bạn dùng JPA Auditing
        Users savedUser = userRepository.save(userEntity);

        // 3. Chuyển đổi Entity đã lưu -> DTO Response
        return usersMapper.toResponse(savedUser);
    }
}