package id.gdplabs.olympia.pts.config.bootstrap;

import javaslang.Function2;
import org.springframework.beans.factory.SmartInitializingSingleton;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

import java.util.Map;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by Deny Prasetyo
 * 24 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


public class EventBootstrap implements SmartInitializingSingleton {

    private EventBus eventBus;
    private Map<String, Consumer> eventMap;
    private Function2<EventBus, Map<String, Consumer>, Void> afterInitFunction = null;

    public EventBootstrap(EventBus eventBus, Map<String, Consumer> eventMap) {
        this.eventBus = eventBus;
        this.eventMap = eventMap;
    }

    public EventBootstrap(EventBus eventBus, Map<String, Consumer> eventMap, Function2<EventBus, Map<String, Consumer>, Void> afterInitFunction) {
        this.eventBus = eventBus;
        this.eventMap = eventMap;
        this.afterInitFunction = afterInitFunction;
    }

    @Override
    public void afterSingletonsInstantiated() {
        eventMap.forEach((key, consumer) -> eventBus.on($(key), consumer));
        if(afterInitFunction != null) {
            afterInitFunction.apply(eventBus, eventMap);
        }
    }
}
