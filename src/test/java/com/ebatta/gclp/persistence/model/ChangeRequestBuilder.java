package com.ebatta.gclp.persistence.model;

public class ChangeRequestBuilder {

    private ChangeRequest changeRequest;

    public ChangeRequestBuilder() {
        changeRequest = new ChangeRequest();
    }

    public ChangeRequestBuilder id(int id) {
        changeRequest.setId(id);
        return this;
    }

    public ChangeRequestBuilder title(String title) {
        changeRequest.setTitle(title);
        return this;
    }

    public ChangeRequestBuilder summary(String summary) {
        changeRequest.setSummary(summary);
        return this;
    }

    public ChangeRequestBuilder detail(String detail) {
        changeRequest.setDetail(detail);
        return this;
    }

    public ChangeRequestBuilder control(String control) {
        changeRequest.setControl(control);
        return this;
    }

    public ChangeRequestBuilder customer(String customer) {
        changeRequest.setCustomer(customer);
        return this;
    }

    public ChangeRequestBuilder risk(RiskEnum risk) {
        changeRequest.setRisk(risk);
        return this;
    }

    public ChangeRequestBuilder state(RequestStateEnum state) {
        changeRequest.setState(state);
        return this;
    }

    public ChangeRequest build() {
        return changeRequest;
    }
}
