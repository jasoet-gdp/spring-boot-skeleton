package id.gdplabs.olympia.pts;

import id.gdplabs.olympia.pts.service.VoucherService;
import id.gdplabs.olympia.pts.value.DiscountType;
import id.gdplabs.olympia.pts.value.TransactionType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class PtsApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PtsApplication.class, args);
        ctx.getBean(VoucherService.class).create("PROMO007", 10, TransactionType.ALL, DiscountType.PERCENTAGE, 20, LocalDate.now().plusDays(35), LocalDate.now().plusDays(45));
    }

}
