package Models;

import java.util.ArrayList;
import java.util.List;

public class CopyReport {
    private final List<String> successfulCopies = new ArrayList<>();
    private final List<String> failedCopies = new ArrayList<>();

    public void addSuccess(String fileName) {
        successfulCopies.add(fileName);
    }

    public void addFailure(String fileName, String reason) {
        failedCopies.add(fileName + " (Причина: " + reason + ")");
    }

    public int getSuccessCount() {
        return successfulCopies.size();
    }

    public int getFailedCount() {
        return failedCopies.size();
    }

    public List<String> getSuccessfulCopies() {
        return successfulCopies;
    }

    public List<String> getFailedCopies() {
        return failedCopies;
    }
}