package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.InvitationFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FriendFriendRepository extends JpaRepository<InvitationFriend,Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value ="insert into invitation_friend (invitation_friend.friend_id,invitation_friend.sender_id,invitation_friend.receiver_id,invitation_friend.is_accept,invitation_friend.created_time) values (default,:senderid,:receiverid,:accept,default)")
    @Transactional
    void senderAddFriend(@Param(value = "senderid")int senderid,
                    @Param(value = "receiverid") int receiverid,
                    @Param(value = "accept") int accept
    );
    @Query(nativeQuery = true, value = "SELECT * FROM invitation_friend where receiver_id =:receiver and sender_id =:sender")
    InvitationFriend getInfoSender(@Param(value = "sender")int sender, @Param(value = "receiver")int receiver);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update invitation_friend set is_accept=:isAccept where sender_id = :senderId and receiver_id = :receiverId")
    void acceptRequest(@Param(value = "receiverId") int receiverId,
                       @Param(value = "senderId") int senderId,
                       @Param(value = "isAccept") int isAccept
    );
}
