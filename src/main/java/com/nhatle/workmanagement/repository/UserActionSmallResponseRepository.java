package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.UserActionSmall;
import com.nhatle.workmanagement.model.response.UserActionSmallResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserActionSmallResponseRepository extends JpaRepository<UserActionSmallResponse, Integer> {
    @Query(nativeQuery = true, value = "SELECT user_action_small.user_action_small_id, user_profile.profile_id, " +
            " action_small.action_small_name,user_profile.avatar, user_profile.full_name," +
            " user_action_small.part," +
            " DATE_FORMAT(user_action_small.time_start, '%Y-%m-%d') as time_start" +
            ",DATE_FORMAT(user_action_small.time_end, '%Y-%m-%d')as time_end " +
            " FROM action_management.action_small join user_action_small" +
            " on action_small.action_small_id=user_action_small.action_small_id" +
            " join user_profile on user_profile.profile_id = user_action_small.profile_id" +
            " join action on action_small.action_id=action.action_id where action_small.action_id =:actionId")
    List<UserActionSmallResponse> getAllUserActionSmall(@Param(value = "actionId") int actionId);
    @Query(nativeQuery = true,
    value = " SELECT user_action_small.user_action_small_id, user_profile.profile_id, " +
            "action_small.action_small_name,user_profile.avatar, user_profile.full_name," +
            " user_action_small.part, DATE_FORMAT(user_action_small.time_start, '%Y-%m-%d') as time_start," +
            "DATE_FORMAT(user_action_small.time_end, '%Y-%m-%d') as time_end " +
            " FROM action_management.action_small join user_action_small" +
            " on action_small.action_small_id=user_action_small.action_small_id" +
            " join user_profile on user_profile.profile_id = user_action_small.profile_id" +
            " join action on action_small.action_id=action.action_id " +
            "where action_small.action_id =:actionId and user_action_small.profile_id =:profileId")
    List<UserActionSmallResponse>getAllActionSmallOfUser(@Param(value = "actionId") int actionId,
                                                         @Param(value = "profileId") int profileId);

}
