package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into group(group_id,group_name,created_time) values (default ,:groupName,default)")
    @Transactional
    void addGroup(@Param(value = ":groupName") String groupName
    );

    @Query(nativeQuery = true, value = "select * from group where group_id =:groupId")
    List<Group> findGroupByGroupId(@Param(value = "groupId") int groupId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM group WHERE group.group_id = :groupId", nativeQuery = true)
    void deleteGroupByGroupId(@Param("groupId") int groupId);

}
