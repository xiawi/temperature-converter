package com.xiawi.tempconverter.model;

public enum Unit {
    CELSIUS {
        @Override
        public String symbol() {
            return "C";
        }
    },
    FAHRENHEIT {
        @Override
        public String symbol() {
            return "F";
        }
    },
    KELVIN {
        @Override
        public String symbol() {
            return "K";
        }
    };

    public abstract String symbol();
}
