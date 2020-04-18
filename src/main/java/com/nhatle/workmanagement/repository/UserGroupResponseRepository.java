package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.response.UserGroupResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupResponseRepository extends JpaRepository<UserGroupResponse,Integer> {
    @Query(nativeQuery = true,
            value = "select user_profile.profile_id,user_profile.avatar,user_profile.full_name,user_profile.addres," +
                    " user_profile.phone_number,user_profile.email , user_team.group_id" +
                    " from user_team " +
                    " join user_profile on user_profile.profile_id = user_team.profile_id " +
                    " join action on action.group_id = user_team.group_id " +
                    " where action.group_id = :groupMakeAction and action.action_id =:actionId")
     List<UserGroupResponse> getAllUserOnGroup(@Param(value = "groupMakeAction") int groupMakeAction,
                                               @Param(value = "actionId") int actionId);
}
