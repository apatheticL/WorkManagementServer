package com.nhatle.workmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "comment_action")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    @Column(name = "profile_id")
    private int profileId;
    @Column(name = "action_id")
    private int actionId;
    @Column(name = "group_id")
    private int groupId;
    private String content;
    @Column(name = "type_content")
    private int type;
    @Column(name = "created_time")
    private String createdTime;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
