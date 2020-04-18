package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.ActionSmall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActionSmallRepository extends JpaRepository<ActionSmall, Integer> {
    @Query(nativeQuery = true,
    value = "select * from action_small where action_id = :actionId")
    List<ActionSmall> getAllActionSmallByActionId(@Param(value = "actionId") int actionId);

    @Query(nativeQuery = true,
            value = "select * from action_small where action_small_id = :actionId")
    ActionSmall getActionSmallByActionSmallId(@Param(value = "actionId") int actionId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = "insert into action_small(action_small_id,action_id,description) values (default ,:actionId,:description)")
    void addActionSmall(@Param(value = "actionId") int actionId,
                        @Param(value = "description") String description);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
    value = "delete from action_small where action_small_id=:actionSmallId")
    void deleteActionSmallByActionSmallId(@Param(value = "actionSmallId") int actionSmallId);



}
