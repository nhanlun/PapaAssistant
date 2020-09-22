package com.example.papaassistant;

import java.io.Serializable;

public class Instruction implements Serializable {
    private int step;
    private String instruction;

    public Instruction(int step, String instruction) {
        this.step = step;
        this.instruction = instruction;
    }

    public Instruction() {

    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}