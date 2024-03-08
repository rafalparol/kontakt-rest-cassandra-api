package inc.temp.right.always.restcassandraapi;

import inc.temp.right.always.restcassandraapi.util.DateAndTimeConversionManager;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateAndTimeConversionManagerTests {
    @Test
    public void fromStringDateToTimestamp_Test() {
        DateAndTimeConversionManager dateAndTimeConversionManager = new DateAndTimeConversionManager();

        Instant expectedResult = Timestamp.valueOf("2024-02-01 00:00:00").toInstant();
        Instant returnedResult = dateAndTimeConversionManager.fromStringDateToInstant("2024-02-01");

        assertEquals(expectedResult, returnedResult, String.format("Expected result: %s and returned result: %s are not the same when querying thermometers with anomalies.", expectedResult, returnedResult));
    }
}
