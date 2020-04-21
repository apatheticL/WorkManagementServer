package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserTeam,Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into user_team (group_id,profile_id) values (:groupId,:profileId)")
    @Transactional
    void addMemberGroup(@Param(value = "groupId")int groupId,
                        @Param(value = "profileId")int profileId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = "delete from user_team where group_id = :groupId and profile_id= :profileId")
    void deleteUserGroups(@Param(value = "groupId") int groupId,
                             @Param(value = "profileId") int profileId);
    @Query(nativeQuery = true,
    value = "select  * from user_team where profile_id = :profileId and group_id = :groupId")
    UserTeam findInfo(int groupId, int profileId);
}
