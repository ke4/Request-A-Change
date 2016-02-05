package com.ebatta.gclp.persistence.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ebatta.gclp.persistence.model.RequestStateEnum;
import com.ebatta.gclp.persistence.model.RiskEnum;

public class ChangeRequestDTO {

    public ChangeRequestDTO() {
    }

    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return id == null;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("ChangeRequest[ ");
        buffer.append("id: ").append(id)
            .append(", title=").append(title);
        
        return buffer.toString();
    }
}
