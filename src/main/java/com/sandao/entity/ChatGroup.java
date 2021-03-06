package com.sandao.entity;

import java.util.Date;

public class ChatGroup {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_group.chat_group_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer chatGroupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_group.chat_group_name
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private String chatGroupName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_group.owner_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer ownerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_group.group_type
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer groupType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chat_group.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_group.chat_group_id
     *
     * @return the value of chat_group.chat_group_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getChatGroupId() {
        return chatGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_group.chat_group_id
     *
     * @param chatGroupId the value for chat_group.chat_group_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_group.chat_group_name
     *
     * @return the value of chat_group.chat_group_name
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public String getChatGroupName() {
        return chatGroupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_group.chat_group_name
     *
     * @param chatGroupName the value for chat_group.chat_group_name
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setChatGroupName(String chatGroupName) {
        this.chatGroupName = chatGroupName == null ? null : chatGroupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_group.owner_id
     *
     * @return the value of chat_group.owner_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_group.owner_id
     *
     * @param ownerId the value for chat_group.owner_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_group.group_type
     *
     * @return the value of chat_group.group_type
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getGroupType() {
        return groupType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_group.group_type
     *
     * @param groupType the value for chat_group.group_type
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chat_group.create_date
     *
     * @return the value of chat_group.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chat_group.create_date
     *
     * @param createDate the value for chat_group.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ChatGroup{" +
                "chatGroupId=" + chatGroupId +
                ", chatGroupName='" + chatGroupName + '\'' +
                ", ownerId=" + ownerId +
                ", groupType=" + groupType +
                ", createDate=" + createDate +
                '}';
    }
}