package id.gdplabs.olympia.pts.listener;

import id.gdplabs.olympia.pts.event.VoucherCreatedEvent;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * Created by Deny Prasetyo
 * 24 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


@Service
public class VoucherCreatedListener implements Consumer<Event<VoucherCreatedEvent>> {
    @Override
    public void accept(Event<VoucherCreatedEvent> voucherCreatedEventEvent) {
        System.out.println(voucherCreatedEventEvent.getData());
    }
}
