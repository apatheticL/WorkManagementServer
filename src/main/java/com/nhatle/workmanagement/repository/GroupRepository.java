package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<Team,Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into team (group_id,group_name,created_time) values (default,:groupName,default)")
    @Transactional
    void addGroup(@Param(value = "groupName") String groupName);

    @Query(nativeQuery = true,value = "SELECT LAST_INSERT_ID()")
    int getIdInserted();
    @Query(nativeQuery = true, value = "select * from team where team.group_id =:groupId")
    Team findGroupByGroupId(@Param(value = "groupId") int groupId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM team WHERE team.group_id = :groupId", nativeQuery = true)
    void deleteGroupByGroupId(@Param("groupId") int groupId);

}
