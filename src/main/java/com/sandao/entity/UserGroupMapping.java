package com.sandao.entity;

import java.util.Date;

public class UserGroupMapping {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_group_mapping.user_group_mapping_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer userGroupMappingId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_group_mapping.user_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_group_mapping.chat_group_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer chatGroupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_group_mapping.mapping_stat
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer mappingStat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_group_mapping.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_group_mapping.user_group_mapping_id
     *
     * @return the value of user_group_mapping.user_group_mapping_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getUserGroupMappingId() {
        return userGroupMappingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_group_mapping.user_group_mapping_id
     *
     * @param userGroupMappingId the value for user_group_mapping.user_group_mapping_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setUserGroupMappingId(Integer userGroupMappingId) {
        this.userGroupMappingId = userGroupMappingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_group_mapping.user_id
     *
     * @return the value of user_group_mapping.user_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_group_mapping.user_id
     *
     * @param userId the value for user_group_mapping.user_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_group_mapping.chat_group_id
     *
     * @return the value of user_group_mapping.chat_group_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getChatGroupId() {
        return chatGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_group_mapping.chat_group_id
     *
     * @param chatGroupId the value for user_group_mapping.chat_group_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_group_mapping.mapping_stat
     *
     * @return the value of user_group_mapping.mapping_stat
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getMappingStat() {
        return mappingStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_group_mapping.mapping_stat
     *
     * @param mappingStat the value for user_group_mapping.mapping_stat
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setMappingStat(Integer mappingStat) {
        this.mappingStat = mappingStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_group_mapping.create_date
     *
     * @return the value of user_group_mapping.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_group_mapping.create_date
     *
     * @param createDate the value for user_group_mapping.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserGroupMapping{" +
                "userGroupMappingId=" + userGroupMappingId +
                ", userId=" + userId +
                ", chatGroupId=" + chatGroupId +
                ", mappingStat=" + mappingStat +
                ", createDate=" + createDate +
                '}';
    }
}