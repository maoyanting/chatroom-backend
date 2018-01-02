package com.sandao.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by maoyanting on 2017/11/30.
 * @author sandao
 */
public class RandomName {
    private String a = "帝高 阳之 苗裔 兮朕 皇考 曰伯 庸摄 提贞 于孟 陬兮 惟庚 寅吾 以降 皇览 揆余 锡余 以嘉 名余 曰正 则兮 字余 灵均 纷吾 既有 此内 美兮 又重 之以 修能 初度" ;
    private String[] c = a.split(" ");
    public String getRandomName() {
        Random random = new Random();
        int it = random.nextInt(30);
        return c[it];
    }
}
