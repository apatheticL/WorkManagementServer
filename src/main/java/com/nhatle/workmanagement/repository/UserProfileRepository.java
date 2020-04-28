package com.nhatle.workmanagement.repository;

import com.nhatle.workmanagement.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    @Query(nativeQuery = true, value = "select * from user_profile where  username = :username")
    UserProfile findOneByUsername(@Param(value = "username") String username);

    @Query(nativeQuery = true, value = "SELECT profile_id,username,password,full_name,avatar,addres," +
            "phone_number,email,DATE_FORMAT(created_time, '%Y-%m-%d') as created_time " +
            "FROM action_management.user_profile where  profile_id = :id")
    UserProfile findOneByProfileId(@Param(value = "id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT into user_profile(profile_id,username,password,avatar,full_name,addres,phone_number,email,created_time)" +
            "values (default ,:username,:pass,:avatar,:fullname,:address,:phone,:email,default)")
    @Transactional
    void insertAccount(@Param(value = "username") String username,
                       @Param(value = "pass") String pass,
                       @Param(value = "avatar") String avatar,
                       @Param(value = "fullname") String fullname,
                       @Param(value = "address") String address,
                       @Param(value = "phone") String phone,
                       @Param(value = "email") String email
    );

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update user_profile set avatar =:avatar, full_name = :fullname,addres = :address,phone_number = :phone,email:email where  profile_id = :id")
    void updateProfile(@Param(value = "id") int id,
                       @Param(value = "avatar") String avatar,
                       @Param(value = "fullname") String fullname,
                       @Param(value = "address") String address,
                       @Param(value = "email") String email,
                       @Param(value = "phone") String phone
    );
    // get all not friend
    @Query(nativeQuery = true,
            value = "SELECT * FROM user_profile WHERE profile_id not in :ids")
    List<UserProfile> findAllNotFriend(
            @Param(value = "ids") List<Integer> ids
    );
}
