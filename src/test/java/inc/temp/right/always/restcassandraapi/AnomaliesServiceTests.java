package inc.temp.right.always.restcassandraapi;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import inc.temp.right.always.restcassandraapi.dto.AnomaliesCount;
import inc.temp.right.always.restcassandraapi.services.AnomaliesService;
import inc.temp.right.always.restcassandraapi.services.custom.CustomAnomaliesRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.QueryOptions;

import java.util.ArrayList;
import java.util.List;

import static inc.temp.right.always.restcassandraapi.services.AnomaliesService.QUERY;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

public class AnomaliesServiceTests {
    @Test
    public void findAllWithAnomaliesCountBiggerThan_NonEmptyList_Test() {
        AnomaliesService anomaliesService = new AnomaliesService();

        CqlTemplate cqlTemplate = mock(CqlTemplate.class);
        ArrayList<AnomaliesCount> mockedResult = new ArrayList<AnomaliesCount>();
        mockedResult.add(new AnomaliesCount("thermometer-1", 5));
        mockedResult.add(new AnomaliesCount("thermometer-2", 2));
        QueryOptions queryOptions = QueryOptions.builder().consistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
        when(cqlTemplate.query(QUERY, new CustomAnomaliesRowMapper(), queryOptions)).thenReturn(mockedResult);

        anomaliesService.setCqlTemplate(cqlTemplate);

        List<AnomaliesCount> returnedResult = anomaliesService.findAllWithAnomaliesCountBiggerThan(1);

        verify(cqlTemplate, times(1)).query(QUERY, new CustomAnomaliesRowMapper(), queryOptions);

        assertIterableEquals(mockedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when querying thermometers with anomalies.", mockedResult, returnedResult));
    }

    @Test
    public void findAllWithAnomaliesCountBiggerThan_EmptyList_Test() {
        AnomaliesService anomaliesService = new AnomaliesService();

        CqlTemplate cqlTemplate = mock(CqlTemplate.class);
        ArrayList<AnomaliesCount> mockedResult = new ArrayList<AnomaliesCount>();
        mockedResult.add(new AnomaliesCount("thermometer-1", 5));
        mockedResult.add(new AnomaliesCount("thermometer-2", 2));
        QueryOptions queryOptions = QueryOptions.builder().consistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
        when(cqlTemplate.query(QUERY, new CustomAnomaliesRowMapper(), queryOptions)).thenReturn(mockedResult);

        anomaliesService.setCqlTemplate(cqlTemplate);

        List<AnomaliesCount> expectedResult = new ArrayList<>();
        List<AnomaliesCount> returnedResult = anomaliesService.findAllWithAnomaliesCountBiggerThan(10);

        verify(cqlTemplate, times(1)).query(QUERY, new CustomAnomaliesRowMapper(), queryOptions);

        assertIterableEquals(expectedResult, returnedResult, String.format("Mocked result: %s and returned result: %s are not the same when querying thermometers with anomalies.", mockedResult, returnedResult));
    }
}
