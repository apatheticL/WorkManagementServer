package com.nhatle.workmanagement.repository;

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
public interface CommentRepository extends JpaRepository<CommentResponse,Integer> {
    @Query(nativeQuery = true, value = "SELECT comment.comment_id, profile.full_name as profile_name, profile.avatar,comment.work_id,comment.content,comment.created_time FROM comment join profile  on comment.profile_id = profile.profile_id where work_id = :idwork")
    List<CommentResponse> getAllCommentByWork(@Param(value = "idwork")int idwork);

    @Modifying
    @Query(nativeQuery = true,
            value ="insert into comment(comment_id,profile_id,work_id,content,created_time) " +
                    "values (default ,:idprofile,:workid,:content,default )")
    @Transactional
    void addComment(@Param(value = "idprofile")int idprofile,
                    @Param(value = "workid") int workid,
                    @Param(value = "content") String content
    );
}
