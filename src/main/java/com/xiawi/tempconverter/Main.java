package com.xiawi.tempconverter;

import com.xiawi.tempconverter.service.ConversionService;
import com.xiawi.tempconverter.storage.HistoryRepository;
import com.xiawi.tempconverter.ui.ConsoleUI;
import com.xiawi.tempconverter.ui.InputHandler;

public class Main {
    static void main() {
        ConversionService conversionService;
        InputHandler inputHandler;
        HistoryRepository historyRepository;
        ConsoleUI ui;

        conversionService = new ConversionService();
        inputHandler = new InputHandler();
        historyRepository = new HistoryRepository();
        ui = new ConsoleUI(inputHandler, conversionService, historyRepository);
        ui.start();
    }
}