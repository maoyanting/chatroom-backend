package com.sandao.web;

import com.alibaba.fastjson.JSON;
import com.sandao.util.JsonDemo;
import com.sandao.entity.SimpleUser;
import com.sandao.entity.User;
import com.sandao.service.impl.UserServiceImpl;
import com.sandao.util.RespUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by maoyanting on 2017/11/23.
 * @author sandao
 */
@Controller
public class PersonalInformationController extends BaseController{

    private UserServiceImpl userService;
    JsonDemo jsonDemo;

    @Autowired
    public void setUserService(UserServiceImpl userService){this.userService = userService;}

    /**
     * 获取用户资料，查询个人资料
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getMyself", method = RequestMethod.GET)
    public void getUserInformation(HttpServletRequest request,HttpServletResponse response){
        User user = getSessionUser(request);
        if (user == null) {
            jsonDemo = new JsonDemo(null,0,"用户不存在");
        } else {
            jsonDemo = new JsonDemo(user,1,"ok");
        }
        RespUtils.writeJson(response,jsonDemo);
    }
    /**
     * 修改密码
     * @param request 用户id，用户旧密码，用户新密码
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/editPassword", method = RequestMethod.GET)
    public String editPassword(HttpServletRequest request)throws IOException{
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String userName = request.getParameter("userName");
        SimpleUser simpleUser = userService.getSimpleUserByUserName(userName);
        if (!simpleUser.getPassword().equals(oldPassword)){
            jsonDemo = new JsonDemo(null,0,"旧的密码输入错误");
        }else {
            //修改旧密码
            userService.editPassword(userName,newPassword);
            jsonDemo = new JsonDemo(null,1,"ok");
        }
        return JSON.toJSONString(jsonDemo);
    }

    /**
     * 更新用户资料
     * @param request
     * @return
     */
    @RequestMapping(value = "/editInformation",method = RequestMethod.GET)
    public String updateInformation(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        String nickname = request.getParameter("nickname");
        String headshot = request.getParameter("headshot");
        String introduction = request.getParameter("introduction");

        User user = userService.getUserById(userId);
        user.setUserNickname(nickname);
        user.setHeadshot(headshot);
        user.setIntroduction(introduction);

        try {
            userService.update(user);
            jsonDemo = new JsonDemo(user,1,"ok");
        }catch (Exception e){
            jsonDemo = new JsonDemo(null,1,"wrong");
        }
        return JSON.toJSONString(jsonDemo);
    }

    @RequestMapping(value = "/getUsers")
    public void getAllUsers(HttpServletResponse response){
        List<User> myFriends = userService.getAllUsers();
        if (myFriends == null) {
            jsonDemo = new JsonDemo(null, 0, "获取失败");
        } else {
            jsonDemo = new JsonDemo(myFriends, 1, "成功");
        }
        RespUtils.writeJson(response,jsonDemo);
    }
}
