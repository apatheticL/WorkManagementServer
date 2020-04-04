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
    private UserActionSmallResponseRepository assignmentRepository;
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
    public Object getInfoProfile(@RequestParam int id){
        return profileRepository.findOneByProfileId(id);
    }
    @PostMapping(value = "/updateProfile")
    public BaseResponse updateProfile(@RequestBody UserProfile profile){
        UserProfile profileOne = new UserProfile();
        profileOne.setProfileId(profile.getProfileId());
        profileOne.setFullName(profile.getFullName());
        profileOne.setPhoneNumber(profile.getPhoneNumber());
        profileOne.setAddress(profile.getAddress());
        profileOne.setAvatar(profile.getAvatar());
        UserProfile profileTwo = profileRepository.findOneByProfileId(profileOne.getProfileId());
        if (profileTwo==null) {
            BaseResponse.createResponse(0, "error when to update");
        }else {
            profileRepository.updateProfile(profileOne.getProfileId(),profileOne.getAvatar(),profileOne.getFullName(),profileOne.getAddress(),profileOne.getEmail(),profileOne.getPhoneNumber());
        }
        return BaseResponse.createResponse(profileOne);
    }

    // friend
    @GetMapping(value = "/getAllFriendByUser")
    public Object getAllFriendByUser(@RequestParam int id) {
        return friendRepository.findAllFriend(id);
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
    public Object getAllUserSender(@RequestParam int idprofile) {
        return friendRepository.getAllFriendSender(idprofile);
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
    @GetMapping(value = "/getAllWorkWithUser")
    public Object getAllWorkWithUser(@RequestParam int userid) {
        return workResponseRepository.getAllWorkByUserMember(userid);
    }
    //insert work
    @PostMapping(value = "/addWork")
    public BaseResponse addWork(@RequestBody Action work) {
        if (work.getActionName().equals("")) {
            return BaseResponse.createResponse(0, "work name not null");
        } else {
            actionRepository.insertAction(work.getActionName(), work.getCreatorId(),work.getGroupId(), work.getTimeStart(),work.getTimeEnd());
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
        if (work1!=null) {
            actionRepository.deleteActionById(idW);
            return true;
        }
        return false;
    }

    //work detail
    @PostMapping(value = "/addGroup")
    public BaseResponse addGroup(@RequestBody Group groupName) {
       if (groupName.getGroupName().isEmpty()){
           BaseResponse.createResponse(0,"group name need not null");
       }
       else {
           groupRepository.addGroup(groupName.getGroupName());
       }
       return BaseResponse.createResponse(groupName);
    }

    // them member trong nhom
    @PostMapping(value = "/addMemberForGroup")
    public boolean addMemberForGroup(@RequestBody UserGroup userGroup) {
        UserGroup workDetails = userGroupRepository.findInfo(userGroup.getGroupId(),userGroup.getProfileId());
        if (workDetails!=null){
            return false;
        }
        userGroupRepository.addMemberGroup(userGroup.getGroupId(),userGroup.getProfileId());
        return true;
    }
// xoa member
    // them action small
    // xoa action small
    //them, xoa , sua user action small
    // comment
    // add report done
    @PostMapping(value = "/addReport")
    public BaseResponse addReport(@RequestBody UserActionReport reportWorkResponse) {
        com.nhatle.workmanagement.model.UserActionSmall workDetails = workDetailRepository.findInfor(reportWorkResponse.getWorkId(), reportWorkResponse.getProfileId());
        if (workDetails == null) {
            workDetailRepository.addReportForWork(reportWorkResponse.getWorkId(),reportWorkResponse.getProfileId(),
                    reportWorkResponse.getWorkPlan(),reportWorkResponse.getWorkActual(),reportWorkResponse.getWorkNext(),
                    reportWorkResponse.getWorkIssua(),reportWorkResponse.getTimeReport());
        }
        reportWorkRepository.addReport(reportWorkResponse.getWorkId(), reportWorkResponse.getProfileId(),
                reportWorkResponse.getWorkPlan(), reportWorkResponse.getWorkIssua(),
                reportWorkResponse.getWorkNext(), reportWorkResponse.getWorkActual(), reportWorkResponse.getTimeReport());
        return BaseResponse.createResponse(reportWorkResponse);
    }

    // get all report done
    @GetMapping(value = "/getAllReport")
    public Object getAllReport(@RequestParam int workid) {
        return reportWorkRepository.getAllReportForWork(workid);
    }

    // get all assignment done
    @GetMapping(value = "/getAllAssignment")
    public Object getAllAssignment(@RequestParam int workid) {
        return assignmentRepository.getAllUserActionSmall(workid);
    }

    @PostMapping(value = "/addAssignment")
    public BaseResponse addAssignment(@RequestBody AssignmentResponse assignmentResponse) {
        com.nhatle.workmanagement.model.UserActionSmall workDetails = workDetailRepository.findInfor(assignmentResponse.getWorkId(), assignmentResponse.getProfileId());
        if (workDetails == null) {
            BaseResponse.createResponse(0, "can not add assignment");
        }
        assignmentRepository.addAssignment(assignmentResponse.getWorkId(), assignmentResponse.getProfileId(), assignmentResponse.getDescription());
        return BaseResponse.createResponse(assignmentResponse);
    }
}
