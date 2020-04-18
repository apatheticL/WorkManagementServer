package com.nhatle.workmanagement.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class CommentResponse {
    @Id
    @Column(name = "comment_id")
    private int commentId;
    @Column(name = "profile_name")
    private int profileName;
    private String avatar;
    @Column(name = "work_id")
    private int workId;
    private String content;
    @Column(name = "created_time")
    private Date createdTime;

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

    public int getProfileName() {
        return profileName;
    }

    public void setProfileName(int profileName) {
        this.profileName = profileName;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
