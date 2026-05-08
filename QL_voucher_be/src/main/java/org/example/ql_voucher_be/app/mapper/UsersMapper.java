package org.example.ql_voucher_be.app.mapper;

import org.example.ql_voucher_be.app.dto.input.UsersRequest;
import org.example.ql_voucher_be.app.dto.output.UsersRespone;
import org.example.ql_voucher_be.domain.entity.Users;
import org.example.ql_voucher_be.infrastructure.persistence.Users.UsersDb;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersMapper {

    // --- PHẦN 1: GIAO TIẾP VỚI CLIENT (DTO) ---

    public Users toEntity(UsersRequest request) {
        if (request == null) return null;
        Users entity = new Users();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPhone(request.phone());
        return entity;
    }

    public UsersRespone toResponse(Users entity) {
        if (entity == null) return null;
        return new UsersRespone(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getCreatedAt()
        );
    }

    public List<UsersRespone> toResponseList(List<Users> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // --- PHẦN 2: GIAO TIẾP VỚI DATABASE (PERSISTENCE) ---
    // Bổ sung để phục vụ cho UsersRepositoryImpl

    /**
     * Chuyển từ Domain Entity (Users) sang Persistence Entity (UsersDb)
     */
    public UsersDb toDbEntity(Users domain) {
        if (domain == null) return null;

        UsersDb db = new UsersDb();
        db.setId(domain.getId());
        db.setFullName(domain.getName()); // Lưu ý: Map name -> fullName
        db.setEmail(domain.getEmail());
        db.setPhone(domain.getPhone());
        db.setCreatedAt(domain.getCreatedAt());

        return db;
    }

    /**
     * Chuyển ngược từ Persistence Entity (UsersDb) sang Domain Entity (Users)
     */
    public Users toDomainEntity(UsersDb db) {
        if (db == null) return null;

        return new Users(
                db.getId(),
                db.getFullName(), // Lưu ý: Map fullName -> name
                db.getEmail(),
                db.getPhone(),
                db.getCreatedAt()
        );
    }
}