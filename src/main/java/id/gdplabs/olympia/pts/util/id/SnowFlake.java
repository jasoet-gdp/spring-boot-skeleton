package id.gdplabs.olympia.pts.util.id;

import id.gdplabs.olympia.pts.util.date.LocalDateTimeUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Deny Prasetyo
 * 22 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


public class SnowFlake {

    public static AtomicInteger increment = new AtomicInteger(0);
    public static final Long EPOCH = 1420045200000L;
    private int serverId;

    public SnowFlake(int serverId) {
        if (serverId >= 64 || serverId < 0) {
            throw new IllegalArgumentException("Machine Number must between 0 - 63");
        }

        this.serverId = serverId;
    }

    public static SnowFlakeId parse(Long id) {
        final Long ts = (id >> 22) + EPOCH;
        final Long max = 64 - 1l;
        final Long machineId = (id >> 16) & max;
        final Long i = id & max;
        return new SnowFlakeId(LocalDateTimeUtil.fromMilliSeconds(ts), machineId.intValue(), i.intValue());
    }

    public static SnowFlakeId parse(String hex) {
        final Long id = Long.parseLong(hex.toLowerCase(), 36);
        return parse(id);
    }


    public synchronized Long nextId() {
        final Long currentTs = System.currentTimeMillis();
        final Long ts = currentTs - SnowFlake.EPOCH;
        final Integer max = 16384 - 2;
        if (increment.get() >= max) {
            increment.set(0);
        }
        final Integer i = increment.incrementAndGet();
        return (ts << 22) | (this.serverId << 16) | i;
    }

    public String nextAlpha() {
        final Long id = nextId();
        return Long.toString(id, 36);
    }


}
