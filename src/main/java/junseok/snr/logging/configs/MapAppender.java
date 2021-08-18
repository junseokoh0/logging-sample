package junseok.snr.logging.configs;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class MapAppender extends AppenderBase<LoggingEvent> {
    @Override
    protected void append(LoggingEvent loggingEvent) {
        MapHolder.getInstance().putEvent(String.valueOf(System.nanoTime()), loggingEvent);
    }
}
