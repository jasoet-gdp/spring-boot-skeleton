package id.gdplabs.olympia.pts.service;

import id.gdplabs.olympia.pts.entity.Voucher;
import id.gdplabs.olympia.pts.event.VoucherCreatedEvent;
import id.gdplabs.olympia.pts.event.VoucherUsedEvent;
import id.gdplabs.olympia.pts.exception.VoucherException;
import id.gdplabs.olympia.pts.repository.VoucherRepository;
import id.gdplabs.olympia.pts.value.DiscountType;
import id.gdplabs.olympia.pts.value.TransactionType;
import javaslang.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private EventBus eventBus;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository, EventBus eventBus) {
        this.voucherRepository = voucherRepository;
        this.eventBus = eventBus;
    }


    public Try<Voucher> use(String code, String transactionId) {
        return Try.of(() -> {
            Voucher voucher = voucherRepository.findByCodeAndValid(code)
                    .stream().findFirst()
                    .orElseThrow(() -> new VoucherException("Voucher Code Invalid"));
            voucher.setTransactionId(transactionId);
            voucherRepository.save(voucher);
            eventBus.notify("voucher-used", Event.wrap(new VoucherUsedEvent(voucher, LocalDateTime.now())));
            return voucher;
        });
    }

    public Try<List<Voucher>> create(String code, int amount,
                                     TransactionType transactionType,
                                     DiscountType discountType,
                                     double value,
                                     LocalDate validFrom,
                                     LocalDate validUntil) {
        return Try.of(() -> {
            List<Voucher> generatedVouchers = IntStream.range(0, amount)
                    .mapToObj(i -> new Voucher(code, transactionType, discountType, value, validFrom, validUntil, true))
                    .collect(Collectors.toList());
            voucherRepository.save(generatedVouchers);
            generatedVouchers.forEach(v -> eventBus.notify("voucher-created", Event.wrap(new VoucherCreatedEvent(v, LocalDateTime.now()))));
            return generatedVouchers;
        });
    }
}
