package org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages;

import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.mapper.VouchersUsageMapper; // File Mapper của bạn
import org.example.ql_voucher_be.domain.entity.Voucher_usages;
import org.example.ql_voucher_be.domain.repository.Voucher_usagesRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VoucherUsageRepositoryImpl implements Voucher_usagesRepository {

    private final VoucherUsageJpaRepository usageJpaRepository;
    private final VouchersUsageMapper usageMapper;

    @Override
    public void saveUsage(Voucher_usages domainEntity) {
        // 1. Dùng mapper chuyển từ Domain sang DB Entity
        VoucherUsageDb dbEntity = usageMapper.toDbEntity(domainEntity);

        // 2. Lưu xuống Database
        usageJpaRepository.save(dbEntity);
    }

    @Override
    public List<Voucher_usages> findAll() {
        // 1. Lấy danh sách từ DB
        List<VoucherUsageDb> dbList = usageJpaRepository.findAll();

        // 2. Dùng mapper chuyển ngược lại danh sách Domain Entity
        return dbList.stream()
                .map(usageMapper::toDomainEntity)
                .collect(Collectors.toList());
    }
}