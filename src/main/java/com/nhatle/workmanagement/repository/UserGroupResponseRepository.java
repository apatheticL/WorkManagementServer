package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.response.UserGroupResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupResponseRepository extends JpaRepository<UserGroupResponse,Integer> {
    @Query(nativeQuery = true,
    value = "select user_group.profile_id,user_group.group_id,user_profile.full_name,user_profile.avatar from user_group join user_profile on user_group.profile_id= user_profile.profile_id where user_group.group_id =:groupId ")
    List<UserGroupResponse> getAllUserOnGroup(@Param(value = ":groupId") int groupId);
}
