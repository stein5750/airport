package airport.gates.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FilePropertyReader {

    private static Logger log = LoggerFactory.getLogger(FilePropertyReader.class);

    private List<String> gateNames;
    private Integer speedFactor;

    public FilePropertyReader() {
        readFiles();
    }

    public List<String> getGateNames() {
        return gateNames;
    }

    public Integer getSpeedFactor() {
        return speedFactor;
    }

    private void readFiles() {

        // read gatenames from file
        try {
            Path filePath = new File("src/main/resources/gateNames.txt").toPath();
            gateNames = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        // read speedFactor from file. The factor used to speed up time.
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/main/resources/speedFactor.txt"));
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        try {
            speedFactor = scanner.nextInt();
        } catch (InputMismatchException e) {
            log.error(e.getMessage());
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        } catch (IllegalStateException e) {
            log.error(e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

}
