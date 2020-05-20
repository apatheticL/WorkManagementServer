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
    private FriendResponseRepository friendResponseRepository;
    @Autowired
    private FriendIdRepository friendIdRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private ActionResponseRepository workResponseRepository;
    @Autowired
    private ActionRepository actionRepository;
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
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentResponseRepository commentResponseRepository;

    //    @Autowired
//    private UserActionRepository userActionRepository;
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
    public BaseResponse register(@RequestBody RegisterResponse registerReponse) {
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
            profileRepository.insertAccount(profile.getUsername(), profile.getPassword(),
                    profile.getAvatar(), profile.getFullName(), profile.getAddress(),
                    profile.getPhoneNumber(), profile.getEmail());

        } else {
            BaseResponse.createResponse(0, "username consist");
        }
        return BaseResponse.createResponse(profile);
    }

    @GetMapping(value = "/getInfoProfile")
    public Object getInfoProfile(@RequestParam int id) {
        return profileRepository.findOneByProfileId(id);
    }

    @PutMapping(value = "/updateProfile")
    public BaseResponse updateProfile(@RequestBody UserProfile profile) {
        UserProfile profileOne = new UserProfile();
        profileOne.setProfileId(profile.getProfileId());
        profileOne.setFullName(profile.getFullName());
        profileOne.setPhoneNumber(profile.getPhoneNumber());
        profileOne.setEmail(profile.getEmail());
        profileOne.setAddress(profile.getAddress());
        profileOne.setAvatar(profile.getAvatar());
        UserProfile profileTwo = profileRepository.findOneByProfileId(profileOne.getProfileId());
//        if (profileTwo == null) {
//            BaseResponse.createResponse(0, "error when to update");
//        } else {
        profileRepository.updateProfile(profileOne.getProfileId(),
                profileOne.getAvatar(), profileOne.getFullName(),
                profileOne.getAddress(), profileOne.getEmail(), profileOne.getPhoneNumber());
//        }
        return BaseResponse.createResponse(profileOne);
    }

    // friend le20051998@gmail.com
    @GetMapping(value = "/getAllFriendByUser")
    public Object getAllFriendByUser(@RequestParam int userId) {
        return friendResponseRepository.findAllFriend(userId);
    }

    @PutMapping(value = "/acceptedFriend")
    public boolean acceptedFriend(@RequestBody InvitationFriend invitationFriend) {
        InvitationFriend a = friendRepository.getInfoSender(invitationFriend.getSenderId(),
                invitationFriend.getReceiverId());
        InvitationFriend invitationFriend1 = new InvitationFriend();
        invitationFriend1.setFriendId(invitationFriend.getFriendId());
        invitationFriend1.setSenderId(invitationFriend.getSenderId());
        invitationFriend1.setReceiverId(invitationFriend.getReceiverId());
        invitationFriend1.setAccept(1);
        invitationFriend1.setCreatedTime(invitationFriend.getCreatedTime());
        if (a == null) {
            return false;
        }
        friendRepository.acceptRequest(invitationFriend1.getReceiverId(), invitationFriend1.getSenderId(),
                invitationFriend1.isAccept(), invitationFriend1.getFriendId());
        return true;
    }

    @DeleteMapping(value = "/deleteInvitationFriend")
    public Boolean deleteInvitationFriend(@RequestParam int friendId) {
        InvitationFriend friend = friendRepository.getInvitationFriendBy(friendId);
        if (friend == null) {
            return false;
        }
        friendRepository.deleteInvitationFriendByFriendId(friendId);
        return true;
    }

    @DeleteMapping(value = "/cancelInvitationFriend")
    public Boolean cancelInvitationFriend(@RequestParam int senderId, @RequestParam int receiverId) {
        InvitationFriend friend = friendRepository.getInfoSender(senderId, receiverId);
        if (friend == null) {
            return false;
        }
        friendRepository.deleteInvitationFriend(senderId, receiverId);
        return true;
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
        return friendResponseRepository.getAllFriendSender(idProfile);
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
        InvitationFriend friend2 = friendRepository.getInfoSender(friend1.getSenderId(),
                friend1.getReceiverId());
        if (friend2 == null) {
            friendRepository.senderAddFriend(friend1.getSenderId(),
                    friend1.getReceiverId(), friend1.isAccept());

        } else {
            BaseResponse.createResponse(0, "sender consist");
        }
        return BaseResponse.createResponse(friend1);
    }

    // work
    // getallwork with member is user
//    @GetMapping(value = "/getAllUserActionSmallIsFinish")
//    public Object getAllUserActionSmallIsFinish(@RequestParam int actionId) {
//        return userActionRepository.getAllUserActionSmallIsFinish(actionId);
//    }

    @GetMapping(value = "/getAllWorkWithUserIsMember")
    public Object getAllWorkWithUserIsMember(@RequestParam int profileId) {
        return workResponseRepository.getAllWork(profileId);
    }


    //insert work
    @PostMapping(value = "/addWork")
    public BaseResponse addWork(@RequestBody Action work) {
        Action action = new Action();
        action.setActionName(work.getActionName());
        action.setActionStatus(work.getActionStatus());
        action.setCreatedTime(work.getCreatedTime());
        action.setCreatorId(work.getCreatorId());
        action.setGroupId(work.getGroupId());
        action.setDescription(work.getDescription());
        action.setTimeStart(work.getTimeStart());
        action.setTimeEnd(work.getTimeEnd());
        if (action.getActionName().equals("")) {
            return BaseResponse.createResponse(0, "work name not null");
        } else {
            actionRepository.insertAction(action.getActionName(), action.getCreatorId(),
                    action.getGroupId(), action.getTimeStart(), action.getTimeEnd(), action.getActionStatus(),
                    action.getDescription());
            int id = actionRepository.getIdInserted();
            action.setActionId(id);
        }
        return BaseResponse.createResponse(action);
    }

    //update
    @PutMapping(value = "/updateWork")
    public BaseResponse updateWork(@RequestBody Action work) {
        Action work1 = actionRepository.findWorkById(work.getActionId());
        Action action = new Action();
        action.setActionId(work.getActionId());
        action.setActionName(work.getActionName());
        action.setActionStatus(work.getActionStatus());
        action.setCreatedTime(work.getCreatedTime());
        action.setCreatorId(work.getCreatorId());
        action.setGroupId(work.getGroupId());
        action.setDescription(work.getDescription());
        action.setTimeStart(work.getTimeStart());
        action.setTimeEnd(work.getTimeEnd());
        if (work1 == null) {
            BaseResponse.createResponse(0, "work not exit");
        } else {
            actionRepository.updateAction(action.getActionId(), action.getActionName()
                    , action.getTimeEnd(), action.getActionStatus(), action.getDescription(), action.getCreatorId());
        }
        return BaseResponse.createResponse(action);
    }

    @PutMapping(value = "/updateStatusWork")
    public boolean updateStatusWork(@RequestParam int id,
                                    @RequestParam String status) {
        if (actionRepository.countActionSmallFish(id) == actionRepository.countUserActionSmall(id)) {
            actionRepository.updateStatusAction(id, status);
            return true;
        }
        return false;
    }

    //deletework dang loi
    @DeleteMapping(value = "/deleteWork")
    public boolean deleteWork(@RequestParam int idW, @RequestParam int profileId) {
        Action work1 = actionRepository.findWorkById(idW);
        if (work1 != null) {
            actionRepository.deleteActionById(idW, profileId);
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

    @DeleteMapping(value = "/deleteGroup")
    public boolean deleteGroup(@RequestParam int groupId) {
        Team group1 = groupRepository.findGroupByGroupId(groupId);
        if (group1 == null) {
            return false;
        }
        groupRepository.deleteGroupByGroupId(groupId);
        return true;
    }

    @PostMapping(value = "/addGroup")
    public BaseResponse addGroup(@RequestBody Team groupName) {
        Team team = groupName;
        if (team.getGroupName().isEmpty()) {
            BaseResponse.createResponse(0, "group name need not null");
        } else {
            groupRepository.addGroup(team.getGroupName());
            int id = groupRepository.getIdInserted();
            team.setGroupId(id);
        }
        return BaseResponse.createResponse(team);
    }

    // them member trong nhom


    // xoa member
    @DeleteMapping(value = "/deleteMemberOnGroup")
    public boolean deleteMemberOnGroup(@RequestBody UserTeam userGroup) {
        UserTeam userGroup1 = userGroupRepository.findInfo(userGroup.getGroupId(), userGroup.getProfileId());
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

    @PostMapping(value = "/addMemberForGroup")
    public List<BaseResponse> addMemberForGroup(@RequestBody List<UserTeam> userGroup) {
        List<BaseResponse> baseResponses = new ArrayList<>();
        List<UserTeam> userTeams = new ArrayList<>();
        for (UserTeam userTem :
                userGroup) {
            UserTeam workDetails = userGroupRepository.findInfo(userTem.getGroupId(), userTem.getProfileId());
            if (workDetails != null) {
                baseResponses.add(BaseResponse.createResponse(0, "Group is not exit"));
            } else {
                userTeams.add(userTem);
                baseResponses.add(BaseResponse.createResponse(userTem));
            }
        }
        userGroupRepository.saveAll(userTeams);
        return baseResponses;
    }

    // them action small
    @PostMapping(value = "/addActionSmall")
    public List<BaseResponse> addActionSmall(@RequestBody List<ActionSmall> actionSmall) {
        List<BaseResponse> baseResponses = new ArrayList<>();
        List<ActionSmall> actionSmalls = new ArrayList<>();
        for (ActionSmall actionSmallOne :
                actionSmall) {

            if (actionSmallOne.getDescription().isEmpty()) {
                baseResponses.add(BaseResponse.createResponse(0, "description is not null"));
            } else {
                actionSmalls.add(actionSmallOne);
                baseResponses.add(BaseResponse.createResponse(actionSmallOne));
            }
        }
        actionSmallRepository.saveAll(actionSmalls);
        return baseResponses;
    }


    // xoa action small
    @DeleteMapping(value = "/deleteActionSmall")
    public boolean deleteActionSmall(@RequestParam int actionSmallId) {
        ActionSmall action = actionSmallRepository.getActionSmallByActionSmallId(actionSmallId);
        if (action == null) {
            return false;
        } else {
            actionSmallRepository.deleteActionSmallByActionSmallId(actionSmallId);
            return true;
        }
    }

    @PutMapping(value = "/updateActionSmall")
    public BaseResponse updateActionSmall(@RequestBody ActionSmall actionSmall) {
        ActionSmall actionS = actionSmallRepository.getActionSmallByActionSmallId(actionSmall.getActionSmallId());
        if (actionS == null) {
            BaseResponse.createResponse(0, "Can not update");
        } else {
            actionSmallRepository.updateActionSmall(actionSmall.getActionSmallId(), actionSmall.getDescription());
        }
        return BaseResponse.createResponse(actionSmall);

    }

    //them, xoa , sua user action small
//laays danh sach action small . tuc ai lam viec gi
    @GetMapping(value = "/getAllUserMakeActionSmall")
    public Object getAllUserMakeActionSmall(@RequestParam int actionId) {
        return userActionSmallResponseRepository.getAllUserActionSmall(actionId);
    }

    @GetMapping(value = "/getAllActionSmallOfUser")
    public Object getAllActionSmallOfUser(@RequestParam int actionId, @RequestParam int profileId) {
        return userActionSmallResponseRepository.getAllActionSmallOfUser(actionId, profileId);
    }

    // add user action small
    @PostMapping(value = "/addUserActionSmall")
    public BaseResponse addUserActionSmall(@RequestBody UserActionSmall userActionSmall) {
        if (userActionSmall.getPart().isEmpty()) {
            return BaseResponse.createResponse(0, "Part is not null");
        }
        UserActionSmall userAction = userActionSmallRepository.findUserActionSmallByActionSmallId(userActionSmall.getActionSmallId(),
                userActionSmall.getProfileId());
        if (userAction!=null){
            return BaseResponse.createResponse(0, "The user is doing this action_small");
        }
        userActionSmallRepository.insertUserAction(userActionSmall.getGroupId(), userActionSmall.getProfileId(),
                userActionSmall.getActionSmallId(), userActionSmall.getPart(), userActionSmall.getTimeStart(),
                userActionSmall.getTimeEnd());
        return BaseResponse.createResponse(userActionSmall);
    }

    @DeleteMapping(value = "/deleteUserActionSmall")
    public boolean deleteUserActionSmall(@RequestParam int userActionSmallId) {
        UserActionSmall action =
                userActionSmallRepository.findUserActionSmall(userActionSmallId);
        if (action == null) {
            return false;
        } else {
            userActionSmallRepository.deleteUserActionSmall(userActionSmallId);
            return true;
        }
    }

    @GetMapping("/getAllActionSmallOnActionOfUser")
    public Object getAllActionSmallOnActionOfUser(@RequestParam int actionId, @RequestParam int profileId) {
        return actionSmallRepository.getAllActionSmallOnActionOfUser(actionId, profileId);
    }
//    @PostMapping(value = "/updateUserActionSmall")
//    public BaseResponse updateUserActionSmall(@RequestBody UserActionSmall userActionSmall) {
//        UserActionSmall action =
//                userActionSmallRepository.findUserActionSmall(userActionSmall.getUserActionSmallId());
//        if (action == null) {
//            BaseResponse.createResponse(0, "user action small not exit");
//        } else {
//            userActionSmallRepository.updateActionSmallByUser(userActionSmall.getGroupId(),
//                    userActionSmall.getProfileId(), userActionSmall.getActionSmallId(),
//                    userActionSmall.getUserActionSmallId(), userActionSmall.getPart(),
//                    userActionSmall.getTimeStart(), userActionSmall.getTimeEnd());
//
//        }
//        return BaseResponse.createResponse(userActionSmall);
//    }

    @GetMapping(value = "/getAllReportOnAction")
    public Object getAllReportOnAction(@RequestParam int actionId) {
        return userReportRepository.getAllReportByActionId(actionId);
    }
    // add report

    @PostMapping(value = "/addReportOnAction")
    public BaseResponse addReportOnAction(@RequestBody UserActionReport userActionReport) {
        if (userActionReport.getActionIssua().isEmpty() || userActionReport.getActionNext().isEmpty()
                || userActionReport.getActionActual().isEmpty()) {
            return BaseResponse.createResponse(0, "it not null");
        }
        UserActionReport userActionReport1 = userActionReportRepository.findReportByUserActionSmall(userActionReport.getUserActionSmallId());
        if (userActionReport1 != null) {
            return BaseResponse.createResponse(0, "it is inserted");
        } else {
            userActionReportRepository.addReport(userActionReport.getUserActionSmallId(),
                    userActionReport.getActionId(), userActionReport.getActionActual(),
                    userActionReport.getActionNext(), userActionReport.getActionIssua());
            return BaseResponse.createResponse(userActionReport);
        }
    }

    @PutMapping(value = "/updateReportOnAction")
    public BaseResponse updateReportOnAction(@RequestBody UserActionReport userActionReport) {
        UserActionReport action =
                userActionReportRepository.findReportByUser(userActionReport.getReportId());
        if (action == null) {
            BaseResponse.createResponse(0, "user not report");
        } else {
            userActionReportRepository.updateReport(userActionReport.getReportId(),
                    userActionReport.getActionActual(), userActionReport.getActionNext(),
                    userActionReport.getActionIssua());

        }
        return BaseResponse.createResponse(userActionReport);
    }

    @DeleteMapping("/deleteReportOnAction")
    public boolean deleteReportOnAction(@RequestParam int reportId) {
        UserActionReport actionReport = userActionReportRepository.findReport(reportId);
        if (actionReport == null) {
            return false;
        }
        userActionReportRepository.deleteUserActionSmall(reportId);
        return true;
    }

    @PostMapping("/sendComment")
    public BaseResponse sendComment(@RequestBody Comment comment) {
        Comment comment1 = comment;
        if (comment1.getContent().isEmpty()) {
            BaseResponse.createResponse(0, "content is not null");
        }
        commentRepository.addComment(comment1.getProfileId(), comment1.getGroupId(), comment1.getActionId(),
                comment1.getContent(), comment1.getType());
        return BaseResponse.createResponse(comment1);
    }

    @DeleteMapping("/deleteCommentOnAction")
    public boolean deleteCommentOnAction(@RequestParam int commentId,
                                         @RequestParam int profileId) {
        Comment comment = commentRepository.findCommentByUser(commentId, profileId);
        if (comment == null) {
            return false;
        }
        commentRepository.deleteCommentByUser(commentId, profileId);
        return true;
    }

    @GetMapping("/getAllCommentOnAction")
    public Object getAllCommentOnAction(@RequestParam int actionId) {
        return commentResponseRepository.getAllCommentByWork(actionId);
    }
}
