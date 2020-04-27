package com.nhatle.workmanagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_action_small")
public class UserActionSmall implements Serializable {
    @Id
    @Column(name = "user_action_small_id")
    private int userActionSmallId;
    @Column(name = "group_id")
    private int groupId;
    @Column(name = "profile_id")
    private int profileId;
    @Column(name = "action_small_id")
    private int actionSmallId;
    private String part;
    @Column(name = "time_start")
    private String timeStart;
    @Column(name = "time_end")
    private String timeEnd;

    public int getUserActionSmallId() {
        return userActionSmallId;
    }

    public void setUserActionSmallId(int userActionSmallId) {
        this.userActionSmallId = userActionSmallId;
    }

    public int getActionSmallId() {
        return actionSmallId;
    }

    public void setActionSmallId(int actionSmallId) {
        this.actionSmallId = actionSmallId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int workId) {
        this.groupId = workId;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String description) {
        this.part = description;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String workPlan) {
        this.timeStart = workPlan;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String workActual) {
        this.timeEnd = workActual;
    }

}
