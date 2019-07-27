package syq.bleg.utils;

import java.util.Random;

/**
 * @Author shiyuquan
 * @Date 2018/9/17 20:51
 * @Description TODO
 */
public class CodeUtil {
    public String getRandNum() {
        String randNum = new Random().nextInt(1000000) + "";
        //如果生成的不是6位数随机数则返回该方法继续生成
        if (randNum.length()!= 6) {
            return getRandNum();
        }
        return randNum;
    }
}
