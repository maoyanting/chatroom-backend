package com.sandao.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * Created by maoyanting on 2017/12/14.
 */
public class RespUtils {
    private static final Logger logger = LoggerFactory.getLogger(RespUtils.class);

    public static void writeJson(final HttpServletResponse response,final JsonDemo jsonDemo){
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application");
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Allow-Credentials","true");
            Writer writer = response.getWriter();
            String json = JSON.toJSONString(jsonDemo);
//            logger.info("write response json:{}",json);
            writer.write(json);
            writer.flush();
            writer.close();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
