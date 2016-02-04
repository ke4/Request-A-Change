package com.ebatta.gclp.persistence.model;

public enum RiskEnum {

    Low(1), Medium(2), High(3);

    private int riskLevel;

    RiskEnum(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public int getRiskLevel() {
        return riskLevel;
    }
    
    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }

    public String getValue() {
        return this.toString();
    }
}
