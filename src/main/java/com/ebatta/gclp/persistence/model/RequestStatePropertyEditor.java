package com.ebatta.gclp.persistence.model;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class RequestStatePropertyEditor extends PropertiesEditor {

    @Override
    public void setAsText(String name) throws IllegalArgumentException {
        RequestStateEnum state = RequestStateEnum.findRequestStateByName(name);
        setValue(state);;
    }
}
