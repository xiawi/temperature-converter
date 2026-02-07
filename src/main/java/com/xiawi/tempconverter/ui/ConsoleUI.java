package com.xiawi.tempconverter.ui;

import com.xiawi.tempconverter.model.ConversionRecord;
import com.xiawi.tempconverter.model.Temperature;
import com.xiawi.tempconverter.model.Unit;
import com.xiawi.tempconverter.service.ConversionService;
import com.xiawi.tempconverter.storage.HistoryRepository;

import java.time.Instant;
import java.util.List;

public class ConsoleUI
{
    private final InputHandler inputHandler;
    private final ConversionService conversionService;
    private final HistoryRepository historyRepository;

    public ConsoleUI(InputHandler inputHandler, ConversionService conversionService, HistoryRepository historyRepository) {
        this.inputHandler = inputHandler;
        this.conversionService = conversionService;
        this.historyRepository = historyRepository;
    }

    public void start() {
        int choice;

        while (true) {
            printMenu();
            choice = inputHandler.readMenuChoice();

            switch (choice) {
                case 1 -> handleConversion();
                case 2 -> showHistory();
                case 3 -> clearHistory();
                case 4 -> exit();
                default -> System.out.println("Invalid option.\n");
            };
        }
    }

    private void printMenu() {
        System.out.println("""
                --== Temperature Converter ==--
                1. Convert Temperature
                2. Show History
                3. Clear History
                4. Exit
                """);
    }

    private void handleConversion() {
        Temperature source;
        Unit targetUnit;
        Temperature converted;
        ConversionRecord record;

        source = inputHandler.readTemperature();
        targetUnit = inputHandler.readTargetUnit();
        converted = conversionService.convert(source, targetUnit);
        record = new ConversionRecord(source, converted, Instant.now());
        historyRepository.addRecord(record);
        System.out.println("Result: " + converted.value() + converted.unit().symbol());
    }

    private void showHistory() {
        List<ConversionRecord> records;

        records = historyRepository.getAllRecords();
        if (records.isEmpty()) {
            System.out.println("No history found.\n");
            return;
        }
        for (ConversionRecord record : records) {
            System.out.println(formatRecord(record));
        }
        System.out.println();
    }

    private String formatRecord(ConversionRecord record) {
        return record.timestamp() + " | " + record.source().value() + record.source().unit().symbol()
                + " → " + record.converted().value() + record.converted().unit().symbol();
    }

    private void clearHistory() {
        historyRepository.clearHistory();
    }

    private void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
