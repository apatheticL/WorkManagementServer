package com.nhatle.workmanagement.model.response;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserActionSmallResponse {
    @Id
    @Column(name = "profile_id")
    private int profileId;
    @Id
    @Column(name = "action_small_id")
    private int actionSmallId;
    private String part;
    @Column(name = "time_start")
    private String timeStart;
    @Column(name = "time_end")
    private String timeEnd;
    @Column(name = "full_name")
    private String fullName;
    private String avatar;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getActionSmallId() {
        return actionSmallId;
    }

    public void setActionSmallId(int actionSmallId) {
        this.actionSmallId = actionSmallId;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
