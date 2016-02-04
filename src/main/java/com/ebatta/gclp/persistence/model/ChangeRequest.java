package com.ebatta.gclp.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="change_request")
public class ChangeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    @Size(min=5, max=150)
    private String title;

    private String summary;
    private String detail;

    @NotEmpty
    private String control;

    @NotEmpty
    @Size(min=2, max=100)
    private String customer;

    @Enumerated(EnumType.STRING)
    private RiskEnum risk;

    @Enumerated(EnumType.STRING)
    private RequestStateEnum state;

    @Transient
    private boolean newCR;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public RiskEnum getRisk() {
        return risk;
    }

    public void setRisk(RiskEnum risk) {
        this.risk = risk;
    }

    public RequestStateEnum getState() {
        return state;
    }

    public void setState(RequestStateEnum state) {
        this.state = state;
    }

    public boolean isNewCR() {
        return id == 0;
    }
}
