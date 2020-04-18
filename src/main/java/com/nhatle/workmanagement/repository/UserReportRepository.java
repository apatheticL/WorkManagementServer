package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.UserActionReport;
import com.nhatle.workmanagement.model.response.UserActionReportRespose;
import com.nhatle.workmanagement.model.response.UserActionSmallResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserReportRepository extends JpaRepository<UserActionReportRespose,Integer> {
    @Query(nativeQuery = true,
            value = "select user_action_report.report_id, user_action_report.group_id, " +
                    "user_action_report.profile_id,user_action_report.action_small_id, " +
                    "user_action_report.action_id,user_profile.avatar,user_profile.full_name, " +
                    "user_action_report.action_actual,user_action_report.action_next, " +
                    "user_action_report.action_issua,user_action_report.time_report  " +
                    "from user_action_report join user_profile on user_profile.profile_id = user_action_report.profile_id " +
                    "join action on user_action_report.action_id = action.action_id " +
                    " where user_action_report.action_id = :actionId")
    List<UserActionReportRespose> getAllReportByActionId(@Param(value = "actionId") int actionId);
}
