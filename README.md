# temperature-converter

## Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Installation](#installation)
- [File Structure](#file-structure)
- [Future Improvements](#future-improvements)

## Overview
Project #1 in my journey to relearn Java. This is a console-based tool for temperature conversions. It supports Celsius, Fahrenheit, and Kelvin conversions with persistent conversion history stored in a file.

## Architecture
This project follows a clean Java structure:
- `model/` → `Temperature`, `Unit`, `ConversionRecord`
- `service/` → `ConversionService` (handles all conversion logic)
- `ui/` → `ConsoleUI`, `InputHandler` (handles input/output)
- `storage/` → `HistoryRepository` (persistent storage of conversion history)
- `Main.java` → wires everything together and launches the application

## Installation
1. Make sure you have Java JDK 21+ installed.
2. Compile the code:
    ```bash
        # From root directory
        mkdir -p out
        javac -d out $(find src/main/java -name "*.java")
    ```
3. Run the program:
    ```bash
        java -cp out com.xiawi.tempconverter.Main
    ```

## File Structure
```
temperature-converter/
├── src/main/java/com/xiawi/tempconverter/
│   ├── model/
│   ├── service/
│   ├── ui/
│   └── storage/
└── Main.java
```

## Future Improvements
- Add automated tests.
- Add a persistent TUI.
