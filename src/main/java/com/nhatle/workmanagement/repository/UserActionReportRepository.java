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
            value = "insert into user_action_report(report_id,profile_id,action_small_id,group_id,action_id,action_actual,action_next,action_issua,time_report) values (default,:profileId,:actionSmallId,:groupId,:actionId,:actionActual,:actionNext,:actionIssua,default)")
    @Transactional
    void addReport(@Param(value = "profileId") int profileId,
                   @Param(value = "actionSmallId") int actionSmallId,
                   @Param(value = "groupId") int groupId,
                   @Param(value = "actionId") int actionId,
                   @Param(value = "actionActual") String actionActual,
                   @Param(value = "actionIssua") String actionIssua
    );


    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "update action_management.user_action_report set action_actual=:actionActual, action_next=:actionNext,action_issua=:actionIssua where report_id=:idReport")
    void updateReport(@Param(value = "idReport") int idReport,
                      @Param(value = "actionActual") String actionActual,
                      @Param(value = "actionNext") String actionNext,
                      @Param(value = "actionIssua") String actionIssua
    );

    @Transactional
    @Modifying
    @Query(value = "delete from user_action_report where group_id =:groupId and profile_id=:profileId and action_small_id = :actionSmallId;",
            nativeQuery = true)
    void deleteUserActionSmall(@Param("actionSmallId") int actionSmallId,
                               @Param("profileId") int profileId,
                               @Param("groupId") int groupId
    );

    @Query(nativeQuery = true,
            value = "select * from user_action_report where profile_id = :profileId")
    List<UserActionReport> getAllReportByProfileId(@Param(value = "profileId") int profileId);

}
