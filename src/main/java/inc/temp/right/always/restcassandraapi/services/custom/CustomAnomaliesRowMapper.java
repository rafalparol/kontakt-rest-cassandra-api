package inc.temp.right.always.restcassandraapi.services.custom;

import com.datastax.oss.driver.api.core.DriverException;
import com.datastax.oss.driver.api.core.cql.Row;
import inc.temp.right.always.restcassandraapi.dto.AnomaliesCount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.RowMapper;

@Data
@NoArgsConstructor
public class CustomAnomaliesRowMapper implements RowMapper<AnomaliesCount> {
    @Override
    public AnomaliesCount mapRow(Row row, int rowNum) throws DriverException {
        AnomaliesCount anomaliesCount = new AnomaliesCount();
        anomaliesCount.setThermometerId(row.getString("thermometerId"));
        anomaliesCount.setAnomalies(row.getLong("anomalies"));
        return anomaliesCount;
    }
}