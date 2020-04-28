package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.response.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentResponseRepository extends JpaRepository<CommentResponse,Integer> {
    @Query(nativeQuery = true,
            value = "select comment_action.comment_id,comment_action.group_id,comment_action.profile_id, user_profile.full_name ," +
                    " user_profile.avatar,comment_action.action_id,comment_action.content,comment_action.type_content" +
                    ", DATE_FORMAT(comment_action.created_time, '%Y-%m-%d') as created_time " +
                    "from comment_action join user_profile  on comment_action.profile_id = user_profile.profile_id " +
                    "where action_id=:actionId")
    List<CommentResponse> getAllCommentByWork(@Param(value = "actionId")int actionId);
}
