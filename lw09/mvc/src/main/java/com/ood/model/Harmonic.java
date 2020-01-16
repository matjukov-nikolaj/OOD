package com.ood.model;

public class Harmonic {

    private double amplitude;

    private HarmonicType type;

    private double frequency;

    private double phase;

    public Harmonic(double amplitude, HarmonicType type, double frequency, double phase) {
        this.amplitude = amplitude;
        this.type = type;
        this.frequency = frequency;
        this.phase = phase;
    }

    public void setType(HarmonicType type) {
        this.type = type;
    }

    public HarmonicType getType() {
        return type;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return amplitude + "*" + type.toString() + "(" + frequency + "*x+" + phase + ")";
    }
}
