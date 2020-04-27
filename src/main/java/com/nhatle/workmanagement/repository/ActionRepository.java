package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM action where action_id = :id")
    Action findWorkById(@Param(value = "id") int id);
// insert
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into action (action_id,action_name, profile_id, group_id ," +
                    "time_start, time_end, created_time,action_status,description) " +
                    "values (default ,:workname,:creator,:groupId,:timestart,:timeend,default,:status,:description )")
    @Transactional
    void insertAction(@Param(value = "workname") String workname,
                      @Param(value = "creator") int creator,
                      @Param(value = "groupId") int groupId,
                      @Param(value = "timestart") String timestart,
                      @Param(value = "timeend") String timeend,
                      @Param(value = "status") String status,
                      @Param(value = "description") String description
    );

    // truoc khi xoa trong bang work phai xoa trong bang detail truoc
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM action WHERE action.action_id= :idW and action.profile_id = :profileId", nativeQuery = true)
    void deleteActionById(@Param("idW") int idW,
                          @Param("profileId") int profileId);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update action set action_name=:workname," +
            " time_start = :timestart,time_end = :timeend ,action_status=:status, description = :description " +
            " where  action_id = :id and profile_id = :profileId")
    void updateAction(@Param(value = "id") int id,
                      @Param(value = "workname") String workname,
                      @Param(value = "timeend") String timeend,
                      @Param(value = "status") String status,
                      @Param(value = "description") String description,
                      @Param(value = "profileId") int profileId
    );

    @Query(nativeQuery = true,
    value = "select count(user_action_report.action_actual) from user_action_report" +
            " where user_action_report.action_actual ='hoan thanh'" +
            " and user_action_report.action_id =:actionId")
    int countActionSmallFish(@Param(value = "actionId") int actionId);
    @Query(nativeQuery = true,
    value = "select count(user_action_small.user_action_small_id)  from action_small" +
            " join  user_action_small  on action_small.action_small_id = user_action_small.action_small_id" +
            " where action_small.action_id = :actionId")
    int countUserActionSmall(@Param(value = "actionId")int actionId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update action set action_status=:status" +
            " where  action_id = :id")
    void updateStatusAction(@Param(value = "id") int id,
                      @Param(value = "status") String status
    );
}
