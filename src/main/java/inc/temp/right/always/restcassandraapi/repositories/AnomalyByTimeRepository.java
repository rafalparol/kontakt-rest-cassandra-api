package inc.temp.right.always.restcassandraapi.repositories;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import inc.temp.right.always.restcassandraapi.model.AnomalyByTime;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Consistency;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AnomalyByTimeRepository extends CassandraRepository<AnomalyByTime, Long> {
    @Consistency(DefaultConsistencyLevel.LOCAL_QUORUM)
    @Query("SELECT * FROM anomalies_by_time WHERE measuredat >= :start AND measuredat <= :end ALLOW FILTERING")
    @Retryable
    List<AnomalyByTime> findAllBetweenStartDateAndEndDate(@Param("start") Instant start, @Param("end") Instant end);
}
