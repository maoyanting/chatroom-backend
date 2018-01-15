# 仿微信web的即时聊天室-后端

## Goal and Philosophy

模仿微信网页版的界面，实现一个即时聊天室

## Why？

偶尔在网上要和不认识的人交流一下，但是作为社交恐惧症的人又不喜欢加微信，就想着如果能不加微信交流就好了。

遂设计了这个项目用于练手

## What’s this？

![chatroom_design_simple](https://github.com/maoyanting/readme_pic/blob/master/chatroom_design_simple.png?raw=true)

实现了群聊，私聊，添加删除好友等功能的web聊天室

新建群：点击设置的新建群聊按钮，新建群聊，群主默认为建群者

添加群用户：通过分享群链接到网上，网友在浏览器输入该链接即可进入该群（需要登录）。

退出群聊：群用户可随时退出群，群主退出群后，群主自动顺延至下一个群用户，如果群主退出群后再无群用户，此群标记为销毁。

添加好友：用户可以通过页面上的搜索框搜索自己想要加的用户，只有在搜索到的用户为非好友情况下，才能添加此用户为好友。

![chatroom_design_addFriend](https://github.com/maoyanting/readme_pic/blob/master/chatroom_design_addFriend.png?raw=true)

好友请求消息：提醒用户收到陌生用户发来的好友请求，可选择同意或者拒绝。回复后，好友请求的发起者会收到回复。

删除好友：在好友资料界面选择删除，好友从好友列表中删除

私聊：在好友资料界面点击聊天按钮开始聊天

## Usage

请在运行tomcat的时候，把tomcat的路径改为/exampleone。不然会导致前端404。

## How It Works

一共有6个MySQL数据库，分别为

chat_group：群信息

friend_request：好友请求

simple_user：用户id，用户名和密码

user：用户的详细信息

user_group_mapping：用户和群之间的关系

user_mapping：用户和用户的关系

其余如下：

![chatroom_design_SSM](https://github.com/maoyanting/readme_pic/blob/master/chatroom_design_SSM.png?raw=true)


## License

Do whatever you want

------

#### 您可以通过以下方式联系到我：

- 邮箱 fhmaoyanting@163.com
- 微信 sandaodaodaodaodao