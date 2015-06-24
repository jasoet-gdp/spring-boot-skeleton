package id.gdplabs.olympia.pts.entity;

import id.gdplabs.olympia.pts.value.DiscountType;
import id.gdplabs.olympia.pts.value.TransactionType;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Created by Deny Prasetyo
 * 22 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */

@Entity
public class Voucher extends AbstractPersistable<Long> {

    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private TransactionType transactionType;
    @Column(nullable = false)
    private DiscountType discountType;
    @Column(nullable = false)
    private Double discountValue;
    @Column(nullable = false)
    private LocalDate validFrom;
    @Column(nullable = false)
    private LocalDate validUntil;
    @Column(nullable = true)
    private String transactionId = "";
    @Column(nullable = false)
    private boolean active;
    public Voucher() {
    }
    public Voucher(String code,
                   TransactionType transactionType,
                   DiscountType discountType,
                   Double discountValue,
                   LocalDate validFrom,
                   LocalDate validUntil,
                   boolean active) {
        this.code = code;
        this.transactionType = transactionType;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.active = active;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "code='" + code + '\'' +
                ", transactionType=" + transactionType +
                ", discountType=" + discountType +
                ", discountValue=" + discountValue +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                ", transactionId='" + transactionId + '\'' +
                ", active=" + active +
                '}';
    }
}