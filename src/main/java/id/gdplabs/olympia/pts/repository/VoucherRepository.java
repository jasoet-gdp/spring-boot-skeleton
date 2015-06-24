package id.gdplabs.olympia.pts.repository;

import id.gdplabs.olympia.pts.entity.Voucher;
import id.gdplabs.olympia.pts.repository.extra.VoucherRepositoryExtra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Deny Prasetyo
 * 22 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


public interface VoucherRepository extends PagingAndSortingRepository<Voucher, Long>, VoucherRepositoryExtra {

    @Query("UPDATE Voucher v SET v.validFrom=:validFrom, v.validUntil=:validUntil WHERE v.code=:code")
    int updateValidDateByCode(@Param("code") String code,
                              @Param("validFrom") LocalDate validFrom,
                              @Param("validUntil") LocalDate validUntil);

    @Query("SELECT v FROM Voucher v WHERE v.code=:code")
    List<Voucher> findByCode(@Param("code") String code);

    @Query("SELECT v FROM Voucher v WHERE v.code=:code AND v.active=:active")
    List<Voucher> findByCodeAndActive(@Param("code") String code, @Param("active") boolean active);


}
