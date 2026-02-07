package com.xiawi.tempconverter.ui;

import com.xiawi.tempconverter.model.Temperature;
import com.xiawi.tempconverter.model.Unit;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int readMenuChoice() {
        String input;

        while (true) {
            System.out.println("Choose an Option:");
            input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public Temperature readTemperature() {
        double value;
        Unit unit;

        value = readDouble("Enter temperature value:");
        unit = readUnit("Enter unit (C/F/K):");
        return new Temperature(value, unit);
    }

    public Unit readTargetUnit() {
        return readUnit("Convert to unit (C/F/K):");
    }

    public double readDouble(String prompt){
        String input;

        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public Unit readUnit(String prompt) {
        String input;

        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();
            try {
                return switch (input) {
                    case "C" -> Unit.CELSIUS;
                    case "F" -> Unit.FAHRENHEIT;
                    case "K" -> Unit.KELVIN;
                    default -> throw new IllegalArgumentException();
                };
            } catch (Exception e) {
                System.out.println("Invalid Unit. Use C, F, or K.");
            }
        }
    }
}