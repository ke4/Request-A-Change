package com.ebatta.gclp.persistence.model;

public enum RequestStateEnum {

    Draft(1), Submitted(2), Approved(3), Rejected(4), MoreInfo(5);

    private int stateLevel;
    
    private RequestStateEnum(int stateLevel) {
        this.stateLevel = stateLevel;
    }

    public int getStateLevel() {
        return stateLevel;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }
}
