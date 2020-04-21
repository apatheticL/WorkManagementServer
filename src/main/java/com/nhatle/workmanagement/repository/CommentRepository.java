package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.Comment;
import com.nhatle.workmanagement.model.response.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {


    @Modifying
    @Query(nativeQuery = true,
            value = "insert into comment_action(comment_id,profile_id,group_id,action_id,content,type_content,created_time)" +
                    " values (default ,:profileId,:groupId,:actionId,:content,:typeContent,default )")
    @Transactional
    void addComment(@Param(value = "profileId") int profileId,
                    @Param(value = "groupId") int groupId,
                    @Param(value = "actionId") int actionId,
                    @Param(value = "content") String content,
                    @Param(value = "typeContent") int typeContent
    );

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM comment_action " +
            "WHERE comment_action.comment_id = :commentId" +
            " and comment_action.profile_id=:profileId", nativeQuery = true)
    void deleteCommentByUser(@Param("commentId") int commentId,
                             @Param("profileId") int profileId);


    @Query(nativeQuery = true,
    value = "select  * from comment_action " +
            "where comment_action.comment_id = :commentId " +
            "and comment_action.profile_id=:profileId")

    Comment findCommentByUser(@Param("commentId") int commentId,
                             @Param("profileId") int profileId);
}
