package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.UserActionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserActionReportRepository extends JpaRepository<UserActionReport, Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into user_action_report(report_id,user_action_small_id," +
                    "action_id,action_actual,action_next,action_issua,time_report) values" +
                    " (default,:userActionSmallId,:actionId,:actionActual,:actionNext,:actionIssua,default)")
    @Transactional
    void addReport(@Param(value = "userActionSmallId") int userActionSmallId,
                   @Param(value = "actionId") int actionId,
                   @Param(value = "actionActual") String actionActual,
                   @Param(value = "actionNext") String actionNext,
                   @Param(value = "actionIssua") String actionIssua
    );


    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "update user_action_report " +
                    "set action_actual=:actionActual, action_next=:actionNext,action_issua=:actionIssua " +
                    " where report_id=:idReport")
    void updateReport(@Param(value = "idReport") int idReport,
                      @Param(value = "actionActual") String actionActual,
                      @Param(value = "actionNext") String actionNext,
                      @Param(value = "actionIssua") String actionIssua
    );

    @Transactional
    @Modifying
    @Query(value = "delete from user_action_report where report_id =:reportId",
            nativeQuery = true)
    void deleteUserActionSmall(@Param("reportId") int reportId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM user_action_report " +
                    "where user_action_report.report_id = :reportId")
    UserActionReport findReportByUser(@Param(value = "reportId") int reportId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM action_management.user_action_report where  report_id = :reportId")
    UserActionReport findReport(@Param(value = "reportId") int reportId);

}
