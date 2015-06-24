package id.gdplabs.olympia.pts.repository.impl;

import id.gdplabs.olympia.pts.entity.Voucher;
import id.gdplabs.olympia.pts.repository.extra.VoucherRepositoryExtra;
import javaslang.control.Try;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Deny Prasetyo
 * 23 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


public class VoucherRepositoryImpl implements VoucherRepositoryExtra {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public boolean isValid(Long id) {


        Try<Voucher> voucherTry = Try.of(() -> entityManager.find(Voucher.class, id));
        if (voucherTry.isFailure()) {
            return false;
        }

        if (!voucherTry.get().isActive()) {
            return false;
        }

        final LocalDate today = LocalDate.now();
        if (voucherTry.get().getValidFrom().isAfter(today) || voucherTry.get().getValidUntil().isBefore(today)) {
            return false;
        }

        return true;
    }

    @Override
    public List<Voucher> findByCodeAndValid(String code) {

        final List<Voucher> voucherList = entityManager.createQuery(
                "SELECT v FROM Voucher v WHERE v.code=:code AND v.active=true",
                Voucher.class).getResultList();
        final LocalDate today = LocalDate.now();
        return voucherList.stream()
                .filter(v -> v.getValidFrom().isBefore(today) && v.getValidUntil().isAfter(today))
                .collect(Collectors.toList());
    }
}
