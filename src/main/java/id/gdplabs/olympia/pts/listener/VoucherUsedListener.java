package id.gdplabs.olympia.pts.listener;

import id.gdplabs.olympia.pts.event.VoucherUsedEvent;
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
public class VoucherUsedListener implements Consumer<Event<VoucherUsedEvent>> {
    @Override
    public void accept(Event<VoucherUsedEvent> voucherUsedEventEvent) {
        System.out.println(voucherUsedEventEvent.getData());
    }
}
