package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.UserActionSmall;
import com.nhatle.workmanagement.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup,Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into user_group(group_id,profile_id) values (:groupId,:profileId)")
    void addMemberGroup(@Param(value = "groupId")int groupId,
                        @Param(value = "profileId")int profileId);

    @Transactional
    @Query(nativeQuery = true,
    value = "delete user_group where group_id = :groupId and profile_id=:profileId")
    boolean deleteUserGroups(@Param(value = "groupId") int groupId,
                             @Param(value = "profileId") int profileId);
    @Query(nativeQuery = true,
    value = "select  * from user_group where profile_id:profileId and groupId = :groupId")
    UserGroup findInfo(int groupId, int profileId);
}
