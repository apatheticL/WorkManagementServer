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
    @Query(nativeQuery = true,value = "select user_action_small.action_small_id, user_action_small.profile_id,user_action_small.part,user_action_small.time_start,user_action_small.time_end, user_profile.full_name, user_profile.avatar from user_action_small join user_profile on user_action_small.profile_id = user_profile.profile_id where group_id = :groupId")
    List<UserActionSmallResponse> getAllUserActionSmall(@Param(value = "groupId")int groupId);


}
