package id.gdplabs.olympia.pts.config;

import id.gdplabs.olympia.pts.config.bootstrap.EventBootstrap;
import id.gdplabs.olympia.pts.listener.VoucherCreatedListener;
import id.gdplabs.olympia.pts.listener.VoucherUsedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Deny Prasetyo
 * 24 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */

@Configuration
public class EventConfig {

    @Bean
    Environment env() {
        return Environment.initializeIfEmpty()
                .assignErrorJournal();
    }

    @Bean
    EventBus createEventBus(Environment env) {
        return EventBus.create(env, Environment.THREAD_POOL);
    }


    @Autowired
    private VoucherCreatedListener voucherCreatedListener;

    @Autowired
    private VoucherUsedListener voucherUsedListener;

    @Bean
    public EventBootstrap bootstrap(EventBus eventBus) {
        Map<String, Consumer> eventMap = new HashMap<>();
        eventMap.put("voucher-used", voucherUsedListener);
        eventMap.put("voucher-created", voucherCreatedListener);

        return new EventBootstrap(eventBus, eventMap);
    }


}
