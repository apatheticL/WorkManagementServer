package com.nhatle.workmanagement.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
public class UserActionSmallResponse {

    @Id
    @Column(name = "user_action_small_id")
    private int userActionSmallId;
    @Column(name = "action_small_name")
    private String actionSmallName;
    @Column(name = "profile_id")
    private int profileId;
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

    public int getUserActionSmallId() {
        return userActionSmallId;
    }

    public void setUserActionSmallId(int userActionSmallId) {
        this.userActionSmallId = userActionSmallId;
    }


    public String getActionSmallName() {
        return actionSmallName;
    }

    public void setActionSmallName(String actionSmallName) {
        this.actionSmallName = actionSmallName;
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
