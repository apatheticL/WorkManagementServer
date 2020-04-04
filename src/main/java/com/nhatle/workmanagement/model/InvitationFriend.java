package com.nhatle.workmanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invitation_friend")
public class InvitationFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private int friendId;
    @Column(name = "sender_id")
    private int senderId;
    @Column(name = "receiver_id")
    private int receiverId;
    @Column(name = "is_accept")
    private int isAccept;
    @Column(name = "created_time")
    private Date createdTime;

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int isAccept() {
        return isAccept;
    }

    public void setAccept(int accept) {
        isAccept = accept;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
