package org.example.ql_voucher_be.infrastructure.persistence.Vouchers;

import lombok.AllArgsConstructor;
import org.example.ql_voucher_be.app.mapper.VoucherMapper;
import org.example.ql_voucher_be.domain.entity.Vouchers;
import org.example.ql_voucher_be.domain.repository.VouchersRepository;
import org.example.ql_voucher_be.infrastructure.persistence.Users.UsersDb;
import org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages.VoucherUsageDb;
import org.example.ql_voucher_be.infrastructure.persistence.Voucher_usages.VoucherUsageJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VoucherRepositoryImpl implements VouchersRepository {

    @Autowired
    private VoucherJpaRepository jpaRepository;
    private VoucherUsageJpaRepository voucherUsageJpaRepository;

    @Override
    public Vouchers save(Vouchers voucher) {
        VouchersDb db = VoucherMapper.toDb(voucher);
        return VoucherMapper.toDomain(jpaRepository.save(db));
    }

    @Override
    public List<Vouchers> findAll() {
        return jpaRepository.findAll().stream()
                .map(VoucherMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Vouchers> findById(Long id) {
        return jpaRepository.findById(id).map(VoucherMapper::toDomain);
    }

    @Override
    public Optional<Vouchers> findByCode(String code) {
        return jpaRepository.findByCode(code).map(VoucherMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
    @Override
    public void recordUsage(Long voucherId, Long userId) {
        VoucherUsageDb usage = new VoucherUsageDb();

        // Gán ID trực tiếp hoặc tìm Entity (Tùy theo JPA của bạn)
        VouchersDb vDb = new VouchersDb(); vDb.setId(voucherId);
        UsersDb uDb = new UsersDb(); uDb.setId(userId);

        usage.setVoucher(vDb);
        usage.setUser(uDb);

        voucherUsageJpaRepository.save(usage);
    }
}