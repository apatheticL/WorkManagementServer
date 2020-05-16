package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.response.ActionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActionResponseRepository extends JpaRepository<ActionResponse, Integer> {
//    @Query(nativeQuery = true , value = "select action.action_id,action.action_name,action.profile_id as profile_id," +
//            " user_profile.full_name, action.time_start, action.time_end,action.group_id, action.created_time, user_profile.avatar" +
//            " from action join user_profile on user_profile.profile_id = action.profile_id" +
//            " where action.profile_id = :userid")
//   List<ActionResponse> getAllWorkByUserCreate(@Param(value = "userid")int userid);

    @Query(nativeQuery = true,
            value = "select distinct action.action_id,action.action_name," +
                    "action.profile_id as profile_creator, team.group_name , " +
                    "(select user_profile.full_name from user_profile where user_profile.profile_id = action.profile_id)" +
                    " as full_name , " +
                    "(select user_profile.avatar from user_profile where user_profile.profile_id = action.profile_id) " +
                    " as avatar " +
                    ",action.group_id,DATE_FORMAT(time_start, '%Y-%m-%d') as time_start," +
                    " DATE_FORMAT(time_end, '%Y-%m-%d') as time_end, action.description," +
                    " DATE_FORMAT(action.created_time, '%Y-%m-%d') as created_time, action.action_status, " +
                    "( select count(user_action_report.report_id) " +
                    "from user_action_report  join user_action_small " +
                    "on user_action_report.user_action_small_id = user_action_small.user_action_small_id " +
                    " where user_action_report.time_report>user_action_small.time_end  " +
                    "and user_action_report.action_id=action.action_id ) as number_delay, " +
                    "(select count(user_action_small.user_action_small_id)  from action_small " +
                    "join  user_action_small  on action_small.action_small_id = user_action_small.action_small_id" +
                    " where action_small.action_id = action.action_id ) as number_action_make, " +
                    " (select count(user_action_report.action_actual) from user_action_report " +
                    " where user_action_report.action_actual ='hoan thanh' " +
                    "and user_action_report.action_id =action.action_id) as number_finish, action.created_time " +
                    "from user_team join user_profile on user_profile.profile_id = user_team.profile_id" +
                    " join action on action.group_id = user_team.group_id join team " +
                    "on user_team.group_id = team.group_id  where user_profile.profile_id = :profileId" +
                    "  or action.profile_id =:profileId order by action.created_time desc ")
    List<ActionResponse> getAllWork(@Param(value = "profileId") int profileId);
}
