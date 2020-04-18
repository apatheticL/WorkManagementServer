package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.response.ActionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActionResponseRepository extends JpaRepository<ActionResponse,Integer> {
    @Query(nativeQuery = true , value = "select action.action_id,action.action_name,action.profile_id as profile_id," +
            " user_profile.full_name, action.time_start, action.time_end,action.group_id, action.created_time, user_profile.avatar" +
            " from action join user_profile on user_profile.profile_id = action.profile_id" +
            " where action.profile_id = :userid")
   List<ActionResponse> getAllWorkByUserCreate(@Param(value = "userid")int userid);

    @Query(nativeQuery = true,value = "select action.action_id,action.action_name,action.profile_id as profile_id," +
            " user_profile.full_name,action.group_id, action.time_start, action.time_end, action.created_time, user_profile.avatar" +
            " from user_team join user_profile on user_profile.profile_id = user_team.profile_id" +
            " join action on action.group_id = user_team.group_id" +
            " where user_profile.profile_id = :profileId and action.profile_id not in (:profileId)")
    List<ActionResponse> getAllWorkByUserIsMember(@Param(value = "profileId")int profileId);
}
