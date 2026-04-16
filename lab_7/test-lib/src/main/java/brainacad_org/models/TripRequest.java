package brainacad_org.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripRequest {
    private String destination;
    private double weight;
    private String cargoType;
    private int requiredExperience;
    private double distance;
    private double payout;
}
