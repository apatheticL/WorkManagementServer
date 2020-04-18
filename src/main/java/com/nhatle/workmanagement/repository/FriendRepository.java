package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.FriendId;
import com.nhatle.workmanagement.model.response.FriendResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FriendRepository extends JpaRepository<FriendResponse, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT " +
                    "invitation_friend.friend_id as id, "+
                    "user_profile.profile_id as friend_id , "+
                    "user_profile.full_name as friend_name, "+
                    "user_profile.username as friend_username, "+
                    "user_profile.avatar as friend_avatar, "+
                    "user_profile.phone_number "+
                    "FROM " +
                    "invitation_friend JOIN user_profile ON " +
                    "(invitation_friend.sender_id = :userId AND invitation_friend.receiver_id = user_profile.profile_id) OR "+
                    "(invitation_friend.receiver_id = :userId AND invitation_friend.sender_id = user_profile.profile_id) "
    )
    List<FriendResponse> findAllFriend(
            @Param(value = "userId") int userId
    );
    @Query(nativeQuery = true, value = "select invitation_friend.friend_id as id,invitation_friend.sender_id as friend_id, user_profile.full_name as friend_name," +
            " user_profile.avatar as friend_avatar," +
            " user_profile.username as friend_username ," +
            " user_profile.phone_number " +
            " from user_profile join invitation_friend on user_profile.profile_id = invitation_friend.sender_id " +
            " where invitation_friend.receiver_id = :idProfile and invitation_friend.is_accept = 0")
    List<FriendResponse> getAllFriendSender(@Param(value = "idProfile") int idProfile);
}
