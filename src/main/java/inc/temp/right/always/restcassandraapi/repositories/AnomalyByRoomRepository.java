package inc.temp.right.always.restcassandraapi.repositories;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import inc.temp.right.always.restcassandraapi.model.AnomalyByRoom;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Consistency;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnomalyByRoomRepository extends CassandraRepository<AnomalyByRoom, String> {
    @Consistency(DefaultConsistencyLevel.LOCAL_QUORUM)
    @Retryable
    List<AnomalyByRoom> findAllByRoomId(String roomId);
}
