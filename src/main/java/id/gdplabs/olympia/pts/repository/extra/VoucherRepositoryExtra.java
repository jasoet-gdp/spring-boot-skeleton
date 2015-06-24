package id.gdplabs.olympia.pts.repository.extra;

import id.gdplabs.olympia.pts.entity.Voucher;

import java.util.List;

/**
 * Created by Deny Prasetyo
 * 23 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


public interface VoucherRepositoryExtra {

    boolean isValid(Long id);

    List<Voucher> findByCodeAndValid(String code);

}
