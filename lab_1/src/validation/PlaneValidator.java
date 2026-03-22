package src.validation;

import java.util.Map;

public class PlaneValidator {
    public static void validate(Map<String, Double> data) throws Exception {
        String key = "tank";

        if (!data.containsKey(key)) {
            throw new Exception("Missing required field in tank file: " + key);
        }

        if (data.get(key) <= 0) {
            throw new Exception("Invalid Configuration: Tank capacity must be greater than 0.");
        }
    }
}
