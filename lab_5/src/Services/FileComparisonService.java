package Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.MismatchRecord;

public class FileComparisonService {
    public List<MismatchRecord> compareFiles(String filePath1, String filePath2) throws IOException {
        List<MismatchRecord> mismatches = new ArrayList<>();

        try (BufferedReader reader1 = new BufferedReader(new FileReader(filePath1));
                BufferedReader reader2 = new BufferedReader(new FileReader(filePath2))) {

            String line1;
            String line2;
            int lineNumber = 1;

            while (true) {
                line1 = reader1.readLine();
                line2 = reader2.readLine();

                if (line1 == null && line2 == null) {
                    break;
                }

                if (line1 == null || line2 == null || !line1.equals(line2)) {
                    mismatches.add(new MismatchRecord(lineNumber, line1, line2));
                }

                lineNumber++;
            }
        }

        return mismatches;
    }
}
