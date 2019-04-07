package com.ood.state.service;

public class QuartersControllerImpl implements QuartersController {

    private static final int MAX_QUARTERS_COUNT = 5;

    private int quartersCount;

    public QuartersControllerImpl() {
        quartersCount = 0;
    }

    public void addQuarter() {
        if (quartersCount < MAX_QUARTERS_COUNT) {
            ++quartersCount;
            System.out.println("Inserted a quarter. Quarters count: " + quartersCount);
        } else {
            System.out.println("You can not insert more than 5 quarters");
        }
    }

    public void useQuarter() {
        if (quartersCount != 0) {
            --quartersCount;
            System.out.println("You used one quarter. Quarters count: " + quartersCount);
        }
    }

    public void returnQuarters() {
        if (quartersCount > 0) {
            System.out.println(quartersCount + " quarter" + (quartersCount != 1 ? "s" : "" + " returned"));
        }
        quartersCount = 0;
    }

    public int getQuartersCount() {
        return quartersCount;
    }

}
