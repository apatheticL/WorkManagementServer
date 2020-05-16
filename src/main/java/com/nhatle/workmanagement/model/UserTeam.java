package com.nhatle.workmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "user_team")
@IdClass(IdUserGroup.class)
public class UserTeam {
    @Id
    @Column(name = "group_id")
    private int groupId;
    @Id
    @Column(name = "profile_id")
    private int profileId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
