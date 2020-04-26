package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.UserActionSmall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserActionSmallRepository extends JpaRepository<UserActionSmall, Integer> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM user_action_small where user_action_small_id = :userActionSmallId")
    UserActionSmall findUserActionSmall(@Param(value = "userActionSmallId") int userActionSmallId);

    @Modifying
    @Query(nativeQuery = true,
            value = "insert into user_action_small(user_action_small_id,group_id,profile_id,action_small_id,part,time_start,time_end) " +
                    "values (default ,:groupId,:profileId,:actionSmallId,:part,:timeStart,:timeEnd) ")
    @Transactional
    void insertUserAction(@Param(value = "groupId") int groupId,
                       @Param(value = "profileId") int profileId,
                       @Param(value = "actionSmallId") int actionSmallId,
                       @Param(value = "part") String part,
                       @Param(value = "timeStart") String timeStart,
                       @Param(value = "timeEnd") String timeEnd
    );
    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "update user_action_small " +
                    "set group_id = :groupId , profile_id = :profileId, action_small_id = :actionSmallId, " +
                    " part=:part,time_start = :timeStart, time_end = :timeEnd" +
                    " where user_action_small_id =:userActionSmallId")
    void updateActionSmallByUser(@Param(value = "groupId") int groupId,
                       @Param(value = "profileId") int profileId,
                       @Param(value = "actionSmallId") int actionSmallId,
                       @Param(value = "userActionSmallId") int userActionSmallId,
                       @Param(value = "part") String part,
                       @Param(value = "timeStart") String timeStart,
                       @Param(value = "timeEnd") String timeEnd
    );
    @Transactional
    @Modifying
    @Query(value = "delete from user_action_small " +
            "where user_action_small_id = :userActionSmallId",
            nativeQuery = true)
    void deleteUserActionSmall(@Param("userActionSmallId") int userActionSmallId
                                  );

}
