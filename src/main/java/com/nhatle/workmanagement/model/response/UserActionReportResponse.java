package com.nhatle.workmanagement.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class UserActionReportResponse {
    @Id
    @Column(name = "report_id")
    private int reportId;
    @Column(name = "user_action_small_id")
    private int userActionSmallId;
    @Column(name = "profile_id")
    private int profileId;
    @Column(name = "action_id")
    private int actionId;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "action_actual")
    private String actionActual;
    @Column(name = "action_next")
    private String actionNext;
    @Column(name = "action_issua")
    private String actionIssua;
    @Column(name = "time_report")
    private String timeReport;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getUserActionSmallId() {
        return userActionSmallId;
    }

    public void setUserActionSmallId(int userActionSmallId) {
        this.userActionSmallId = userActionSmallId;
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

    public void setActionId(int actionId) {
        this.actionId = actionId;
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

    public String getActionActual() {
        return actionActual;
    }

    public void setActionActual(String actionActual) {
        this.actionActual = actionActual;
    }

    public String getActionNext() {
        return actionNext;
    }

    public void setActionNext(String actionNext) {
        this.actionNext = actionNext;
    }

    public String getActionIssua() {
        return actionIssua;
    }

    public void setActionIssua(String actionIssua) {
        this.actionIssua = actionIssua;
    }

    public String getTimeReport() {
        return timeReport;
    }

    public void setTimeReport(String timeReport) {
        this.timeReport = timeReport;
    }
}
