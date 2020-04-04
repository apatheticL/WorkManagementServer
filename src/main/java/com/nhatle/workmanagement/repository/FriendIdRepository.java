package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendIdRepository extends JpaRepository<FriendId,Integer> {
    @Query(nativeQuery = true,
            value = "SELECT friend_id as id, sender_id, receiver_id " +
                    "FROM friend " +
                    "WHERE sender_id = :userId OR " +
                    "receiver_id = :userId ")
    List<FriendId> findAllNotFriend(
            @Param(value = "userId") int userId
    );

}
