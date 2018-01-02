package com.sandao.exception;

/**
 * Created by maoyanting on 2017/11/17.
 */
public class UserExistException extends Exception {
    public UserExistException(String errorMsg)
    {
        super(errorMsg);
    }
}
