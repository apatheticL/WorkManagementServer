package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.response.UserActionRespose;
import com.nhatle.workmanagement.model.response.UserActionSmallResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserActionRepository extends JpaRepository<UserActionRespose,Integer> {
    @Query(nativeQuery = true,
            value = "SELECT distinct user_action_small.user_action_small_id," +
                    " action_small.action_small_name,user_profile.avatar, user_profile.full_name, " +
                    " user_action_small.part, user_action_small.time_start,user_action_small.time_end ," +
                    " user_action_report.action_actual" +
                    " FROM user_action_small join action_small " +
                    " on action_small.action_small_id=user_action_small.action_small_id " +
                    " join user_profile on user_profile.profile_id = user_action_small.profile_id" +
                    "  join user_action_report " +
                    " on user_action_small.user_action_small_id=user_action_report.user_action_small_id " +
                    " where action_small.action_id =:actionId ")
    List<UserActionSmallResponse> getAllUserActionSmallIsFinish(@Param(value = "actionId") int actionId);

}
