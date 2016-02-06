package com.ebatta.gclp.persistence.model;

import java.util.HashMap;
import java.util.Map;

public enum RequestStateEnum {

    Draft("Draft"), Submitted("Submitted"), Approved("Approved"),
    Rejected("Rejected"), MoreInfo("More information needed");

    private String name;
    private static Map<String,RequestStateEnum> nameEnumMap;
    
    private RequestStateEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RequestStateEnum findRequestStateByName(String name) {
        if (nameEnumMap == null) {
            createMap();
        }

        return nameEnumMap.get(name);
    }

    private static void createMap() {
        nameEnumMap = new HashMap<>();

        for (RequestStateEnum state : values()) {
            nameEnumMap.put(state.getName(), state);
        }
    }
}
