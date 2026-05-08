package org.example.ql_voucher_be.infrastructure.persistence.Vouchers;

import org.example.ql_voucher_be.app.mapper.VoucherMapper;
import org.example.ql_voucher_be.domain.entity.Vouchers;
import org.example.ql_voucher_be.domain.repository.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VoucherRepositoryImpl implements VouchersRepository {

    @Autowired
    private VoucherJpaRepository jpaRepository;

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
}