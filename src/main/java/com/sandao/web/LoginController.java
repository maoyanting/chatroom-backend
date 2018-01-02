package com.sandao.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sandao.util.CommonConstant;
import com.sandao.util.JsonDemo;
import com.sandao.entity.SimpleUser;
import com.sandao.entity.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static com.sandao.util.CommonConstant.USER_CONTEXT;

/**
 * Created by maoyanting on 2017/11/20.
 * @author sandao
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    JsonDemo jsonDemo;
    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestBody SimpleUser user) throws IOException{
        String userName = user.getUserName();
        String password = user.getPassword();
        SimpleUser dbUser = userService.getSimpleUserByUserName(userName);
        User loginUser = userService.getUserByUserName(userName);
        if (dbUser == null) {
            jsonDemo = new JsonDemo(null,0,"用户不存在或者用户名错误");
        } else if (!dbUser.getPassword().equals(password)) {
            jsonDemo = new JsonDemo(null,0,"密码错误");
        } else {
            setSessionUser(request,loginUser);
            jsonDemo = new JsonDemo(loginUser,1,"ok");
        }
        logger.info("--------Login In---------");
        logger.info("userName:{}",userName);
        logger.info("password:{}",password);
        logger.info(JSON.toJSONString(jsonDemo));
        logger.info(JSON.toJSONString(getSessionUser(request)));
        RespUtils.writeJson(response,jsonDemo);
    }
    /**
     * 登录注销
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(USER_CONTEXT);
        return "forward:/index.jsp";
    }

    /**
     * 根据传入的用户名，查找这个用户名是否有对应的用户
     * @param user
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/register/checkUserName", method = RequestMethod.POST)
    public void checkUserName(@RequestBody SimpleUser user, HttpServletResponse response) throws IOException {
        String userName = user.getUserName();
        User userFind = userService.getUserByUserName(userName);
        if (userFind != null) {
            jsonDemo=new JsonDemo(null,0,"用户名已经被使用");
        }else {
            jsonDemo=new JsonDemo(null,1,"用户名可使用");
        }
        RespUtils.writeJson(response,jsonDemo);
    }

    /**
     * 注册
     *
     * @param request
     * @return user信息
     * @throws IOException
     */
    @RequestMapping(value = "/register")
    public void succeed(@RequestBody SimpleUser userRegister, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = userRegister.getUserName();
        String password = userRegister.getPassword();
        SimpleUser userFind = userService.getSimpleUserByUserName(username);
        if (userFind != null) {
            jsonDemo=new JsonDemo(null,0,"用户名已经被使用");
        } else {
            User user =userService.register(username,password,1);
            setSessionUser(request,user);
            jsonDemo = new JsonDemo(user,1,"ok");
        }
        RespUtils.writeJson(response,jsonDemo);
    }

    /**
     * 游客登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/visitor")
    public String visitorLogin(HttpServletRequest request){
        User user = userService.visitorRegister(request.getSession().getId());
        setSessionUser(request,user);
        jsonDemo = new JsonDemo(user,1,"ok");
        return JSON.toJSONString(jsonDemo);
    }
}
