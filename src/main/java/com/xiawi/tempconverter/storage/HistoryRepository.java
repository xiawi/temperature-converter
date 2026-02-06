package com.xiawi.tempconverter.storage;

import com.xiawi.tempconverter.model.ConversionRecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    private static final String FILENAME = "converter-history.txt";
    private final Path filePath;

    public HistoryRepository() {
        this.filePath = Paths.get(FILENAME);
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            if (Files.notExists(filePath))
                Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create history file.", e);
        }
    }

    public void addRecord(ConversionRecord record) {
        String line;

        line = record.toFileString();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath,StandardOpenOption.APPEND)) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write history record.", e);
        }
    }

    public List<ConversionRecord> getAllRecords() {
        List<ConversionRecord> records;
        String line;

        records = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            while ((line = reader.readLine()) != null) {
                try {
                    records.add(ConversionRecord.fromFileString(line));
                } catch (Exception ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read history.", e);
        }
        return records;
    }

    public void clearHistory() {
        try {
            Files.write(filePath, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to clear history", e);
        }
    }
}