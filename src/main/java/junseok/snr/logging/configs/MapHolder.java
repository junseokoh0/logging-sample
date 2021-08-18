package junseok.snr.logging.configs;

import ch.qos.logback.classic.spi.LoggingEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapHolder {

    private Map<String, LoggingEvent> eventMap = new ConcurrentHashMap<>();
    private static MapHolder MAP_INSTANCE = null;

    public static MapHolder getInstance() {
        if (MAP_INSTANCE == null) {
            MAP_INSTANCE = new MapHolder();
        }
        return MAP_INSTANCE;
    }

    public void putEvent(String time, LoggingEvent event) {
        eventMap.put(time, event);
    }

    public Map<String, LoggingEvent> getEventMap() {
        return eventMap;
    }
}
