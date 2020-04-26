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
            value = "insert into action (action_id,action_name, profile_id, group_id , time_start, time_end, created_time,description) " +
                    "values (default ,:workname,:creator,:groupId,:timestart,:timeend,default,:description )")
    @Transactional
    void insertAction(@Param(value = "workname") String workname,
                      @Param(value = "creator") int creator,
                      @Param(value = "groupId") int groupId,
                      @Param(value = "timestart") String timestart,
                      @Param(value = "timeend") String timeend,
                      @Param(value = "description") String description
    );

    // truoc khi xoa trong bang work phai xoa trong bang detail truoc
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM action WHERE action.action_id= :idW", nativeQuery = true)
    void deleteActionById(@Param("idW") int idW);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update action set action_name=:workname," +
            " time_start = :timestart,time_end = :timeend where  action_id = :id")
    void updateAction(@Param(value = "id") int id,
                      @Param(value = "workname") String workname,
                      @Param(value = "timeend") String timeend,
                      @Param(value = "description") String description
    );
}
