package com.sandao.entity;

import java.util.Date;

public class UserMapping {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_mapping.user_mapping_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer userMappingId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_mapping.user_a_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer userAId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_mapping.user_b_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer userBId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_mapping.mapping_type
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer mappingType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_mapping.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_mapping.user_mapping_id
     *
     * @return the value of user_mapping.user_mapping_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getUserMappingId() {
        return userMappingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_mapping.user_mapping_id
     *
     * @param userMappingId the value for user_mapping.user_mapping_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setUserMappingId(Integer userMappingId) {
        this.userMappingId = userMappingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_mapping.user_a_id
     *
     * @return the value of user_mapping.user_a_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getUserAId() {
        return userAId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_mapping.user_a_id
     *
     * @param userAId the value for user_mapping.user_a_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setUserAId(Integer userAId) {
        this.userAId = userAId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_mapping.user_b_id
     *
     * @return the value of user_mapping.user_b_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getUserBId() {
        return userBId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_mapping.user_b_id
     *
     * @param userBId the value for user_mapping.user_b_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setUserBId(Integer userBId) {
        this.userBId = userBId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_mapping.mapping_type
     *
     * @return the value of user_mapping.mapping_type
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getMappingType() {
        return mappingType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_mapping.mapping_type
     *
     * @param mappingType the value for user_mapping.mapping_type
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setMappingType(Integer mappingType) {
        this.mappingType = mappingType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_mapping.create_date
     *
     * @return the value of user_mapping.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_mapping.create_date
     *
     * @param createDate the value for user_mapping.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserMapping{" +
                "userMappingId=" + userMappingId +
                ", userAId=" + userAId +
                ", userBId=" + userBId +
                ", mappingType=" + mappingType +
                ", createDate=" + createDate +
                '}';
    }
}