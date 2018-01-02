package com.sandao.web;

import com.alibaba.fastjson.JSON;
import com.sandao.entity.FriendRequest;
import com.sandao.entity.SimpleUser;
import com.sandao.entity.UserMapping;
import com.sandao.util.JsonDemo;
import com.sandao.entity.User;
import com.sandao.service.impl.NoticeServiceImpl;
import com.sandao.service.impl.UserServiceImpl;
import com.sandao.util.RespUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sandao
 *         Created by maoyanting on 2017/11/23.
 *         好友模块
 */
@Controller
public class FriendsController extends BaseController {

    private UserServiceImpl userService;
    private NoticeServiceImpl noticeService;
    JsonDemo jsonDemo;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setNoticeService(NoticeServiceImpl noticeService) {
        this.noticeService = noticeService;
    }

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 获取好友列表
     *
     * @param user
     * @param response
     */
    @RequestMapping(value = "/friendList", method = RequestMethod.POST)
    public void friendList(@RequestBody SimpleUser user, HttpServletResponse response) {
        int userId = user.getUserId();
        List<User> myFriends = userService.getUserFriends(userId);
        if (myFriends.size() == 0) {
            jsonDemo = new JsonDemo(null, 0, "没有好友");
        } else {
            jsonDemo = new JsonDemo(myFriends, 1, "获取好友列表成功");
        }
        logger.info("------------get friend list-------------");
        logger.info(JSON.toJSONString(jsonDemo));
        RespUtils.writeJson(response, jsonDemo);
    }

    /**
     * 查找用户，同时返回是否是好友
     *
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    public void searchUser(@RequestBody SimpleUser user,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        String userName = user.getUserName();
        User searchUser = userService.getUserByUserName(userName);
        User me = getSessionUser(request);
        if (searchUser == null) {
            jsonDemo = new JsonDemo(null, 0, "没有找到对应的用户");
        } else {
            boolean isFriend = userService.isFriend(me.getUserId(), searchUser.getUserId());
            if (isFriend) {
                jsonDemo = new JsonDemo(searchUser, 2, "这个是你的好友哦");
            } else {
                jsonDemo = new JsonDemo(searchUser, 1, "查找成功");
            }
        }
        RespUtils.writeJson(response, jsonDemo);
    }

    /**
     *
     * @param user
     * @param response
     */
    @RequestMapping(value = "/searchUserById", method = RequestMethod.POST)
    public void searchUserById(@RequestBody SimpleUser user,
                               HttpServletResponse response) {
        try {
            int userId = user.getUserId();
            User searchUser = userService.getUserById(userId);
            jsonDemo = new JsonDemo(searchUser, 1, "查找成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RespUtils.writeJson(response, jsonDemo);
    }

    /**
     * 发送好友请求
     *
     * @param friendRequest
     * @param response
     * @return
     */
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public void sendFriendRequest(@RequestBody FriendRequest friendRequest,
                                  HttpServletResponse response) {
        int initiatorId = friendRequest.getInitiatorId();
        int acceptorId = friendRequest.getAcceptorId();
        try {
            noticeService.addFriendRequest(initiatorId, acceptorId);
            jsonDemo = new JsonDemo(null, 1, "好友请求发送成功");
        } catch (Exception e) {
            jsonDemo = new JsonDemo(null, 0, "好友请求发送失败");
        }
        logger.info(JSON.toJSONString(jsonDemo));
        RespUtils.writeJson(response, jsonDemo);
    }

    @RequestMapping(value = "/deleteFriend", method = RequestMethod.POST)
    public void deleteFriend(@RequestBody UserMapping userMapping,
                             HttpServletResponse response) {
        try {
            int myId = userMapping.getUserAId();
            int friendId = userMapping.getUserBId();
            userService.deleteFriend(myId, friendId);
            jsonDemo = new JsonDemo(null, 1, "删除好友成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonDemo = new JsonDemo(null, 0, "出错了！");
        }
        RespUtils.writeJson(response, jsonDemo);
    }
}
