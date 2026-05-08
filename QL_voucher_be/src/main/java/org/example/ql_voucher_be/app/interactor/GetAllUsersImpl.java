package org.example.ql_voucher_be.app.interactor;

import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.dto.output.UsersRespone;
import org.example.ql_voucher_be.app.mapper.UsersMapper;
import org.example.ql_voucher_be.app.usecase.IGetAllUsers;
import org.example.ql_voucher_be.domain.entity.Users;
import org.example.ql_voucher_be.domain.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllUsersImpl implements IGetAllUsers {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Override
    public List<UsersRespone> execute() {
        // 1. Gọi Repository ở tầng Domain để lấy danh sách Entity
        List<Users> usersList = usersRepository.findAll();

        // 2. Sử dụng Mapper để chuyển đổi List<Users> (Entity) sang List<UsersRespone> (DTO)
        return usersMapper.toResponseList(usersList);
    }
}