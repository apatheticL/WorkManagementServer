package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.response.UserActionReportResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserReportRepository extends JpaRepository<UserActionReportResponse,Integer> {
    @Query(nativeQuery = true,
            value = "select distinct user_action_report.report_id, user_action_report.user_action_small_id," +
                    " user_action_small.profile_id," +
                    " user_action_report.action_id,user_profile.avatar,user_profile.full_name, " +
                    "  user_action_report.action_actual,user_action_report.action_next, " +
                    "  user_action_report.action_issua," +
                    " DATE_FORMAT(user_action_report.time_report, '%Y-%m-%d') as time_report  " +
                    " from user_action_small join user_profile on user_action_small.profile_id = user_profile.profile_id" +
                    "  join user_action_report on user_action_report.user_action_small_id = user_action_small.user_action_small_id " +
                    "  where user_action_report.action_id = :actionId")
    List<UserActionReportResponse> getAllReportByActionId(@Param(value = "actionId") int actionId);
}
