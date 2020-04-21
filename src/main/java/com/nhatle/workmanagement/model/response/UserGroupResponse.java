package com.nhatle.workmanagement.model.response;

import com.nhatle.workmanagement.model.IdUserGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(IdUserGroup.class)
public class UserGroupResponse {
    @Id
    @Column(name = "profile_id")
    private int profileId;
   @Id
    @Column(name = "group_id")
    private int groupId;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "full_name")
    private String fullName;
    @Column(name ="addres")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
