package com.nhatle.workmanagement.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CommentResponse {
    @Id
    @Column(name = "comment_id")
    private int commentId;
    @Column(name = "profile_id")
    private int profileId;
    @Column(name = "group_id")
    private int groupId;
    @Column(name = "full_name")
    private String fullName;
    private String avatar;
    @Column(name = "action_id")
    private int actionId;
    private String content;
    @Column(name = "type_content")
    private int typeContent;
    @Column(name = "created_time")
    private String createdTime;

    public int getType() {
        return typeContent;
    }

    public void setType(int type) {
        this.typeContent = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int workId) {
        this.actionId = workId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
