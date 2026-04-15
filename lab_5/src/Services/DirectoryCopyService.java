package Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import Models.CopyReport;

public class DirectoryCopyService {
    public CopyReport copyFiles(String sourceDirPath, String destDirPath) throws IOException {
        CopyReport report = new CopyReport();
        File sourceDir = new File(sourceDirPath);
        Path destPath = Paths.get(destDirPath);

        if (!Files.exists(destPath)) {
            Files.createDirectories(destPath);
        }

        File[] files = sourceDir.listFiles();

        if (files == null || files.length == 0) {
            return report;
        }

        for (File file : files) {
            if (file.isFile()) {
                Path sourceFilePath = file.toPath();
                Path destFilePath = destPath.resolve(file.getName());

                try {
                    Files.copy(sourceFilePath, destFilePath, StandardCopyOption.REPLACE_EXISTING);
                    report.addSuccess(file.getName());
                } catch (IOException e) {
                    report.addFailure(file.getName(), e.getMessage());
                }
            }
        }

        return report;
    }
}
