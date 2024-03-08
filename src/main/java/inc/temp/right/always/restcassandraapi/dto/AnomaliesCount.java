package inc.temp.right.always.restcassandraapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnomaliesCount {
    private String thermometerId;
    private long anomalies;
}
