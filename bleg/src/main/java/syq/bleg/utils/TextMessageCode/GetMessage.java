package syq.bleg.utils.TextMessageCode;

import syq.bleg.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syq.bleg.utils.TextMessageCode.CodeUtil;
import syq.bleg.utils.TextMessageCode.Config;
import syq.bleg.utils.TextMessageCode.HttpUtil;

import java.net.URLEncoder;

/**
 * @Author shiyuquan
 * @Date 2018/9/17 21:10
 * @Description TODO
 */
@Service
public class GetMessage {

    @Autowired
    RedisService redisService;

    private String operation = "/industrySMS/sendSMS";

    private String accountSid = Config.ACCOUNT_SID;
    /**
     * 验证码通知短信
     */
    public String execute(String tel){
        HttpUtil httpUtil = new HttpUtil();
        CodeUtil codeUtil = new CodeUtil();
        String code = codeUtil.getRandNum();
        String smsContent = "【宝驰信科技】尊敬的用户，您的验证码为"+code;
        String tmpSmsContent = null;
        try{
            tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
        }catch(Exception e){

        }
        String url = Config.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + tel + "&smsContent=" + tmpSmsContent
                + httpUtil.createCommonParam();
        String result = httpUtil.post(url, body);

        char[] res = result.toCharArray();
        String codeResult = "";
        for(int i=13; i<=17; i++){
            codeResult = codeResult + String.valueOf(res[i]);
        }
        if("00000".equals(codeResult)) {
            redisService.set(tel, code);
        }
        return codeResult;
    }
}