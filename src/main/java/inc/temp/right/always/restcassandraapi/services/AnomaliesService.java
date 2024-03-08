package inc.temp.right.always.restcassandraapi.services;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.Row;
import inc.temp.right.always.restcassandraapi.dto.AnomaliesCount;
import inc.temp.right.always.restcassandraapi.services.custom.CustomAnomaliesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.QueryOptions;
import org.springframework.data.cassandra.core.cql.RowMapper;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnomaliesService {
    @Autowired
    private CqlTemplate cqlTemplate;
    public static final String QUERY = "SELECT thermometerid AS thermometerId, COUNT(*) AS anomalies FROM anomalies_by_thermometer GROUP BY thermometerid ALLOW FILTERING";

    @Retryable
    public List<AnomaliesCount> findAllWithAnomaliesCountBiggerThan(int threshold) {
//      QueryOptions queryOptions = QueryOptions.builder().consistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
        return cqlTemplate.query(QUERY, new CustomAnomaliesRowMapper()).stream().filter(ac -> ac.getAnomalies() > threshold).collect(Collectors.toList());
    }

    public void setCqlTemplate(CqlTemplate cqlTemplate) {
        this.cqlTemplate = cqlTemplate;
    }
}
