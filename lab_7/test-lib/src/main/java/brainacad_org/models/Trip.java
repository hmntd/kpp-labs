package brainacad_org.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trip {
    private TripRequest request;
    private Driver driver;
    private Car car;
    private boolean isCompleted;
}
