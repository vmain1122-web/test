package com.example.demo;

public class BrewedDrink {
    private String name;
    private int bitterness; // 苦味
    private int sweetness;  // 甘味
    private int cyberPulse; // 電波度

    public BrewedDrink(String name, int bitterness, int sweetness, int cyberPulse) {
        this.name = name;
        this.bitterness = bitterness;
        this.sweetness = sweetness;
        this.cyberPulse = cyberPulse;
    }

    // Getters
    public String getName() { return name; }
    public int getBitterness() { return bitterness; }
    public int getSweetness() { return sweetness; }
    public int getCyberPulse() { return cyberPulse; }
}