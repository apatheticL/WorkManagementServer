package com.nhatle.workmanagement.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class ActionResponse {
    @Id
    @Column(name = "action_id")
    private int actionId;
    @Column(name = "action_name")
    private String actionName;
    @Column(name = "profile_id")
    private int creatorId;
    @Column(name = "group_id")
    private int groupId;
    @Column(name = "full_name")
    private String nameCreator;
    @Column(name = "time_start")
    private String timeStart;
    @Column(name = "time_end")
    private String timeEnd;
    @Column(name = "created_time")
    private String createdTime;
    private String avatar;

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int workId) {
        this.actionId = workId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String workName) {
        this.actionName = workName;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getNameCreator() {
        return nameCreator;
    }

    public void setNameCreator(String nameCreator) {
        this.nameCreator = nameCreator;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
