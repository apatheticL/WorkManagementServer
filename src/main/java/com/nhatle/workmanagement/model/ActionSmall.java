package com.nhatle.workmanagement.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "action_small")
public class ActionSmall {
    @Id
    @Column(name = "action_small_id")
    private int actionSmallId;
    @Column(name ="action_id")
    private int actionId;
    private String description;

    public int getActionSmallId() {
        return actionSmallId;
    }

    public void setActionSmallId(int actionSmallId) {
        this.actionSmallId = actionSmallId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
