package inc.temp.right.always.restcassandraapi.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.*;

@Component
public class DateAndTimeConversionManager {
    public Instant fromStringDateToInstant(String str) {
        return Timestamp.valueOf(str + " 00:00:00").toInstant();
    }
}
