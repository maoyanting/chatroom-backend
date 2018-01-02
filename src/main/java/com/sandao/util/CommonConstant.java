package com.sandao.util;

/**
 * Created by maoyanting on 2017/11/20.
 */
public class CommonConstant {
    /**
     * 用户对象放到Session中的键名称
     */
    public static final String USER_CONTEXT = "USER_CONTEXT";
    /**
     * 用户对象放到Session中的键名称
     */
    public static final String SIMPLE_USER_CONTEXT = "SIMPLE_USER_CONTEXT";
    /**
     * 将登录前的URL放到Session中的键名称
     */
    public static final String LOGIN_TO_URL = "toUrl";

    /**
     * 每页的记录数
     */
    public static final int PAGE_SIZE = 3;
    /**
     * webSocket发出的好友请求
     */
    public static final int MESSAGE_TYPE_FRIEND_REQUEST = 3;
    /**
     * webSocket发出的好友请求回复
     */
    public static final int MESSAGE_TYPE_FRIEND_REQUEST_REPLY = 4;
    /**
     * webSocket发出的session
     */
    public static final int MESSAGE_TYPE_SESSION = 0;
    /**
     * webSocket发出的私聊
     */
    public static final int MESSAGE_TYPE_PERSONAL = 1;
    /**
     * webSocket发出的群聊
     */
    public static final int MESSAGE_TYPE_CHAT_GROUP = 2;
    /**
     * 回复好友请求，同意
     */
    public static final int FRIEND_REQUEST_REPLY_AGREE = 1;
    /**
     * 回复好友请求，拒绝
     */
    public static final int FRIEND_REQUEST_REPLY_REFUSE = 0;
    /**
     * 我们不是朋友了呢
     */
    public static final int USER_MAPPING_NOT_FRIEND = 0;
    /**
     * 我们还是朋友
     */
    public static final int USER_MAPPING_IS_FRIEND = 1;
}
