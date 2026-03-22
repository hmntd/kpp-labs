package src.validation;

import java.util.Map;

public class DistanceValidator {
    public static void validate(Map<String, Double> data) throws Exception {
        String[] requiredKeys = { "distAB", "distBC", "weight" };

        for (String key : requiredKeys) {
            if (!data.containsKey(key)) {
                throw new Exception("Missing required field in distance file: " + key);
            }
            if (data.get(key) < 0) {
                throw new Exception("Field " + key + " cannot be negative.");
            }
        }

        if (data.get("weight") > 2000) {
            throw new Exception("Flight impossible: Weight exceeds 2000kg limit.");
        }
    }
}
