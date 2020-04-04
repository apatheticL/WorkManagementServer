package com.nhatle.workmanagement.model.response;

import javax.persistence.Column;

public class UserGroupResponse {
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "group_id")
    private int groupId;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
