package syq.bleg.base.textmessagecode;

/**
 * @Author shiyuquan
 * @Date 2018/9/17 21:12
 */
public class TextMessageConfig {

    private TextMessageConfig() {}

    public String S = "s";

    /**
     * url前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822";

    /**
     * 开发者注册后系统自动生成的账号，可在官网登录后查看
     */
    public static final String ACCOUNT_SID = "114e02e7a1c344f58b5abde18eb1946b";

    /**
     * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
     */
    public static final String AUTH_TOKEN = "5a4a30084dc143d29cd0af62dd619f25";

    /**
     * 响应数据类型, JSON或XML
     */
    public static final String RESP_DATA_TYPE = "json";
}
