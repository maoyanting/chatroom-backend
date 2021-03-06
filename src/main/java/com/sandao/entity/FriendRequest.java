package com.sandao.entity;

import java.util.Date;

public class FriendRequest {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.friend_request_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer friendRequestId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.initiator_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer initiatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.acceptor_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer acceptorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.condition
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Integer conditionNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_request.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.friend_request_id
     *
     * @return the value of friend_request.friend_request_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getFriendRequestId() {
        return friendRequestId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.friend_request_id
     *
     * @param friendRequestId the value for friend_request.friend_request_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setFriendRequestId(Integer friendRequestId) {
        this.friendRequestId = friendRequestId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.initiator_id
     *
     * @return the value of friend_request.initiator_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getInitiatorId() {
        return initiatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.initiator_id
     *
     * @param initiatorId the value for friend_request.initiator_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setInitiatorId(Integer initiatorId) {
        this.initiatorId = initiatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.acceptor_id
     *
     * @return the value of friend_request.acceptor_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getAcceptorId() {
        return acceptorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.acceptor_id
     *
     * @param acceptorId the value for friend_request.acceptor_id
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setAcceptorId(Integer acceptorId) {
        this.acceptorId = acceptorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.condition
     *
     * @return the value of friend_request.condition
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Integer getConditionNumber() {
        return conditionNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.condition
     *
     * @param condition the value for friend_request.condition
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setConditionNumber(Integer conditionNumber) {
        this.conditionNumber = conditionNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_request.create_date
     *
     * @return the value of friend_request.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_request.create_date
     *
     * @param createDate the value for friend_request.create_date
     *
     * @mbg.generated Tue Nov 21 15:40:10 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "friendRequestId=" + friendRequestId +
                ", initiatorId=" + initiatorId +
                ", acceptorId=" + acceptorId +
                ", conditionNumber=" + conditionNumber +
                ", createDate=" + createDate +
                '}';
    }
}