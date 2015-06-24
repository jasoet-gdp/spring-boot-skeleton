package id.gdplabs.olympia.pts.event;

import id.gdplabs.olympia.pts.entity.Voucher;

import java.time.LocalDateTime;

/**
 * Created by Deny Prasetyo
 * 24 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


public class VoucherCreatedEvent {
    public VoucherCreatedEvent(Voucher voucher, LocalDateTime time) {
        this.voucher = voucher;
        this.time = time;
    }

    private Voucher voucher;
    private LocalDateTime time;

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "VoucherCreatedEvent{" +
                "voucher=" + voucher +
                ", time=" + time +
                '}';
    }
}
