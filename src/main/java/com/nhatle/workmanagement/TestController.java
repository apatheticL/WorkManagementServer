package com.nhatle.workmanagement;

import com.nhatle.workmanagement.model.*;
import com.nhatle.workmanagement.model.response.*;
import com.nhatle.workmanagement.repository.*;
import com.nhatle.workmanagement.repository.UserActionSmallResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    private UserProfile profile = new UserProfile();
    @Autowired
    private UserProfileRepository profileRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private FriendIdRepository friendIdRepository;
    @Autowired
    private FriendFriendRepository friendFriendRepository;
    @Autowired
    private ActionResponseRepository workResponseRepository;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private UserActionReportRepository reportWorkRepository;
    @Autowired
    private UserActionSmallResponseRepository userActionSmallResponseRepository;
    @Autowired
    private UserActionSmallRepository userActionSmallRepository;
    @Autowired
    private ActionSmallRepository actionSmallRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private UserGroupResponseRepository userGroupResponseRepository;
    @Autowired
    private UserReportRepository userReportRepository;
    @Autowired
    private UserActionReportRepository userActionReportRepository;
    // profile
    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody LoginResponse loginRequest) {
        profile = profileRepository.findOneByUsername(loginRequest.getUsername());

        if (profile == null || !profile.getPassword().equals(loginRequest.getPassword())) {
            return BaseResponse.createResponse(0, "username or password invalid ");
        }
        return BaseResponse.createResponse(profile);
    }

    @PostMapping(value = "/register")
    public BaseResponse register(@RequestBody RegisterReponse registerReponse) {
        UserProfile profile = new UserProfile();
        profile.setUsername(registerReponse.getUsername());
        profile.setPassword(registerReponse.getPassword());
        profile.setAddress(registerReponse.getAddres());
        profile.setAvatar(registerReponse.getAvatar());
        profile.setFullName(registerReponse.getFull_name());
        profile.setPhoneNumber(registerReponse.getPhone());
        if (profile.getUsername().equals("") || profile.getPassword().equals("")) {
            BaseResponse.createResponse(0, "username and password not null");
        }
        UserProfile profile1 = profileRepository.findOneByUsername(profile.getUsername());
        if (profile1 == null) {
            profileRepository.insertAccount(profile.getUsername(), profile.getPassword(), profile.getAvatar(), profile.getFullName(), profile.getAddress(), profile.getPhoneNumber(), profile.getEmail());

        } else {
            BaseResponse.createResponse(0, "username consist");
        }
        return BaseResponse.createResponse(profile);
    }

    @GetMapping(value = "/getInfoProfile")
    public Object getInfoProfile(@RequestParam int id) {
        return profileRepository.findOneByProfileId(id);
    }

    @PostMapping(value = "/updateProfile")
    public BaseResponse updateProfile(@RequestBody UserProfile profile) {
        UserProfile profileOne = new UserProfile();
        profileOne.setProfileId(profile.getProfileId());
        profileOne.setFullName(profile.getFullName());
        profileOne.setPhoneNumber(profile.getPhoneNumber());
        profileOne.setAddress(profile.getAddress());
        profileOne.setAvatar(profile.getAvatar());
        UserProfile profileTwo = profileRepository.findOneByProfileId(profileOne.getProfileId());
        if (profileTwo == null) {
            BaseResponse.createResponse(0, "error when to update");
        } else {
            profileRepository.updateProfile(profileOne.getProfileId(), profileOne.getAvatar(), profileOne.getFullName(), profileOne.getAddress(), profileOne.getEmail(), profileOne.getPhoneNumber());
        }
        return BaseResponse.createResponse(profileOne);
    }

    // friend le20051998@gmail.com
    @GetMapping(value = "/getAllFriendByUser")
    public Object getAllFriendByUser(@RequestParam int userId) {
        return friendRepository.findAllFriend(userId);
    }

    @GetMapping(value = "/getAllNotFriend")
    public Object getAllNotFriend(@RequestParam int idUser) {
        List<FriendId> friendIds = friendIdRepository.findAllNotFriend(idUser);
        List<Integer> fIds = new ArrayList<>();
        fIds.add(idUser);
        for (FriendId friendId : friendIds) {
            if (friendId.getReceiverId() == idUser) {
                fIds.add(friendId.getSenderId());
            } else {
                fIds.add(friendId.getReceiverId());
            }
        }
        return
                profileRepository.findAllNotFriend(fIds);
    }


    @GetMapping(value = "/getAllUserSenderFriend")
    public Object getAllUserSender(@RequestParam int idProfile) {
        return friendRepository.getAllFriendSender(idProfile);
    }

    // gui loi moi ket ban
    @PostMapping(value = "/senderFriend")
    public BaseResponse senderFriend(@RequestBody InvitationFriend friend) {
        InvitationFriend friend1 = new InvitationFriend();
        friend1.setFriendId(friend.getFriendId());
        friend1.setSenderId(friend.getSenderId());
        friend1.setReceiverId(friend.getReceiverId());
        friend1.setAccept(friend.isAccept());
        friend1.setCreatedTime(friend.getCreatedTime());
        InvitationFriend friend2 = friendFriendRepository.getInfoSender(friend1.getSenderId(), friend1.getReceiverId());
        if (friend2 == null) {
            friendFriendRepository.senderAddFriend(friend1.getSenderId(), friend1.getReceiverId(), friend1.isAccept());

        } else {
            BaseResponse.createResponse(0, "sender consist");
        }
        return BaseResponse.createResponse(friend1);
    }

    // work
    // getallwork with member is user
    @GetMapping(value = "/getAllWorkWithUserCreate")
    public Object getAllWorkWithUserCreate(@RequestParam int userid) {
        return workResponseRepository.getAllWorkByUserCreate(userid);
    }

    @GetMapping(value = "/getAllWorkWithUserIsMember")
    public Object getAllWorkWithUserIsMember(@RequestParam int profileId) {
        return workResponseRepository.getAllWorkByUserIsMember(profileId);
    }


    //insert work
    @PostMapping(value = "/addWork")
    public BaseResponse addWork(@RequestBody Action work) {
        if (work.getActionName().equals("")) {
            return BaseResponse.createResponse(0, "work name not null");
        } else {
            actionRepository.insertAction(work.getActionName(), work.getCreatorId(), work.getGroupId(), work.getTimeStart(), work.getTimeEnd());
        }
        return BaseResponse.createResponse(work);
    }

    //update
    @PostMapping(value = "/updateWork")
    public BaseResponse updateWork(@RequestBody Action work) {
        Action work1 = actionRepository.findWorkById(work.getActionId());
        if (work1 == null) {
            BaseResponse.createResponse(0, "work not exit");
        } else {
            actionRepository.updateAction(work.getActionId(), work.getActionName(), work.getTimeStart(), work.getTimeEnd());
        }
        return BaseResponse.createResponse(work);
    }

    //deletework dang loi
    @PostMapping(value = "/deleteWork")
    public boolean deleteWork(@RequestParam int idW) {
        Action work1 = actionRepository.findWorkById(idW);
        if (work1 != null) {
            actionRepository.deleteActionById(idW);
            return true;
        }
        return false;
    }
// getAll chuwa tesst
    //lay danh sach thanh vien lam viec do
    @GetMapping(value = "/getAllMemberOnActionInGroup")
    public Object getAllMemberOnActionInGroup(@RequestParam int groupMakeAction, @RequestParam int actionId) {
        return userGroupResponseRepository.getAllUserOnGroup(groupMakeAction, actionId);
    }

    @PostMapping(value = "/deleteGroup")
    public boolean deleteGroup(@RequestParam int groupId){
        Group group1 = groupRepository.findGroupByGroupId(groupId);
        if (group1 ==null){
            return false;
        }
        groupRepository.deleteGroupByGroupId(groupId);
        return true;
    }

    @PostMapping(value = "/addGroup")
    public BaseResponse addGroup(@RequestBody Group groupName) {
        if (groupName.getGroupName().isEmpty()) {
            BaseResponse.createResponse(0, "group name need not null");
        } else {
            groupRepository.addGroup(groupName.getGroupName());
        }
        return BaseResponse.createResponse(groupName);
    }

    // them member trong nhom
    @PostMapping(value = "/addMemberForGroup")
    public BaseResponse addMemberForGroup(@RequestBody UserGroup userGroup) {
        UserGroup workDetails = userGroupRepository.findInfo(userGroup.getGroupId(), userGroup.getProfileId());
        if (workDetails != null) {
            return BaseResponse.createResponse(0,"Group is not exit");
        }
        userGroupRepository.addMemberGroup(userGroup.getGroupId(), userGroup.getProfileId());
        return BaseResponse.createResponse(userGroup);
    }

    // xoa member
    @PostMapping(value = "/deleteMemberOnGroup")
    public boolean deleteMemberOnGroup(@RequestBody UserGroup userGroup) {
        UserGroup userGroup1 = userGroupRepository.findInfo(userGroup.getGroupId(), userGroup.getProfileId());
        if (userGroup1 == null) {
            return false;
        }
        userGroupRepository.deleteUserGroups(userGroup.getGroupId(), userGroup.getProfileId());
        return true;
    }
    // get all action small by action id

    @GetMapping("/getAllActionSmallByAction")
    public Object getAllActionSmallByAction(@RequestParam int actionId) {
        return actionSmallRepository.getAllActionSmallByActionId(actionId);
    }

    // them action small
    @PostMapping(value = "/addActionSmall")
    public BaseResponse addActionSmall(@RequestBody ActionSmall actionSmall) {
        if (actionSmall.getDescription().isEmpty()) {
            return BaseResponse.createResponse(0, "description is not null");
        }
        actionSmallRepository.addActionSmall(actionSmall.getActionId(), actionSmall.getDescription());
        return BaseResponse.createResponse(actionSmall);
    }

    // xoa action small
    @PostMapping(value = "/deleteActionSmall")
    public boolean deleteActionSmall(@RequestParam int actionSmallId) {
        ActionSmall action = actionSmallRepository.getActionSmallByActionSmallId(actionSmallId);
        if (action == null) {
            return false;
        } else {
            actionSmallRepository.deleteActionSmallByActionSmallId(actionSmallId);
            return true;
        }
    }

    //them, xoa , sua user action small
//laays danh sach action small . tuc ai lam viec gi
    @GetMapping(value = "/getAllUserMakeActionSmall")
    public Object getAllUserMakeActionSmall(@RequestParam int actionId) {
        return userActionSmallResponseRepository.getAllUserActionSmall(actionId);
    }

    // add user action small
    @PostMapping(value = "/addUserActionSmall")
    public BaseResponse addUserActionSmall(@RequestBody UserActionSmall userActionSmall) {
        if (userActionSmall.getPart().isEmpty()) {
            return BaseResponse.createResponse(0, "Part is not null");
        }
        userActionSmallRepository.insertUserAction(userActionSmall.getGroupId(), userActionSmall.getProfileId(), userActionSmall.getActionSmallId(), userActionSmall.getPart(), userActionSmall.getTimeStart(), userActionSmall.getTimeEnd());
        return BaseResponse.createResponse(userActionSmall);
    }

    @PostMapping(value = "/deleteUserActionSmall")
    public boolean deleteUserActionSmall(@RequestParam int groupId, @RequestParam int profileId, @RequestParam int actionSmallId) {
        UserActionSmall action = userActionSmallRepository.findUserActionSmall(groupId, profileId, actionSmallId);
        if (action == null) {
            return false;
        } else {
            userActionSmallRepository.deleteUserActionSmall(actionSmallId, profileId, groupId);
            return true;
        }
    }

    @PostMapping(value = "/updateUserActionSmall")
    public BaseResponse updateUserActionSmall(@RequestBody UserActionSmall userActionSmall) {
        UserActionSmall action = userActionSmallRepository.findUserActionSmall(userActionSmall.getGroupId(), userActionSmall.getProfileId(), userActionSmall.getActionSmallId());
        if (action == null) {
            BaseResponse.createResponse(0, "user action small not exit");
        } else {
            userActionSmallRepository.updateActionSmallByUser(userActionSmall.getGroupId(), userActionSmall.getProfileId(), userActionSmall.getActionSmallId(), userActionSmall.getPart(), userActionSmall.getTimeStart(), userActionSmall.getTimeEnd());

        }
        return BaseResponse.createResponse(userActionSmall);
    }

    @GetMapping(value = "/getAllReportOnAction")
    public Object getAllReportOnAction(@RequestParam int actionId){
        return userReportRepository.getAllReportByActionId(actionId);
    }
    // add report

    @PostMapping(value = "/addReportOnAction")
    public BaseResponse addReportOnAction(@RequestBody UserActionReport userActionReport){
        if (userActionReport.getActionIssua().isEmpty()||userActionReport.getActionNext().isEmpty()||userActionReport.getActionActual().isEmpty()){
            return BaseResponse.createResponse(0,"it not null");
        }
        userActionReportRepository.addReport(userActionReport.getProfileId(),userActionReport.getActionSmallId(),userActionReport.getGroupId(),userActionReport.getActionId(),userActionReport.getActionActual(),userActionReport.getActionNext(),userActionReport.getActionIssua());
        return BaseResponse.createResponse(userActionReport);
    }
    @PostMapping(value ="/updateReportOnAction")
    public BaseResponse updateReportOnAction(@RequestBody UserActionReport userActionReport) {
        List<UserActionReport> action = userActionReportRepository.findReportByUser(userActionReport.getProfileId(),userActionReport.getActionId());
        if (action == null) {
            BaseResponse.createResponse(0, "user not report");
        } else {
            userActionReportRepository.updateReport(userActionReport.getReportId(),userActionReport.getActionActual(),userActionReport.getActionNext(),userActionReport.getActionIssua());

        }
        return BaseResponse.createResponse(userActionReport);
    }

    @PostMapping("/deleteReportOnAction")
    public boolean deleteReportOnAction(@RequestParam int reportId){
        UserActionReport actionReport = userActionReportRepository.findReport(reportId);
        if (actionReport==null){
            return false;
        }
        userActionReportRepository.deleteUserActionSmall(reportId);
        return true;
    }

}
