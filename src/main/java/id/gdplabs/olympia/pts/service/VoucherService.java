package id.gdplabs.olympia.pts.service;

import id.gdplabs.olympia.pts.entity.Voucher;
import id.gdplabs.olympia.pts.exception.VoucherException;
import id.gdplabs.olympia.pts.repository.VoucherRepository;
import id.gdplabs.olympia.pts.value.DiscountType;
import id.gdplabs.olympia.pts.value.TransactionType;
import javaslang.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Deny Prasetyo
 * 23 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


@Service
public class VoucherService {

    private VoucherRepository voucherRepository;


    @Autowired
    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }


    public Try<Voucher> use(String code, String transactionId) {
        return Try.of(() -> {
            Voucher voucher = voucherRepository.findByCodeAndValid(code)
                    .stream().findFirst()
                    .orElseThrow(() -> new VoucherException("Voucher Code Invalid"));
            voucher.setTransactionId(transactionId);
            voucherRepository.save(voucher);
            return voucher;
        });
    }

    public List<Voucher> create(String code, int amount,
                                TransactionType transactionType,
                                DiscountType discountType,
                                double value,
                                LocalDate validFrom,
                                LocalDate validUntil) {
        List<Voucher> generatedVouchers = IntStream.range(0, amount)
                .mapToObj(i -> new Voucher(code, transactionType, discountType, value, validFrom, validUntil, true))
                .collect(Collectors.toList());
        voucherRepository.save(generatedVouchers);
        return generatedVouchers;
    }
}
