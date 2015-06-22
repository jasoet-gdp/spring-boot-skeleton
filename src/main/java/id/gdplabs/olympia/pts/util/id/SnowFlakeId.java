package id.gdplabs.olympia.pts.util.id;

import java.time.LocalDateTime;

/**
 * Created by Deny Prasetyo
 * 22 June 2015
 * Principal Software Development Engineer
 * GDP Labs
 * deny.prasetyo@gdplabs.id
 */


public class SnowFlakeId {

    public SnowFlakeId(LocalDateTime timestamp, int serverId, int increment) {
        this.timestamp = timestamp;
        this.serverId = serverId;
        this.increment = increment;
    }

    private LocalDateTime timestamp;
    private int serverId;
    private int increment;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
