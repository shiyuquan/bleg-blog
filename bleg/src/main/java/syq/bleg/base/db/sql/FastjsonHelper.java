package syq.bleg.base.db.sql;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;

import java.io.IOException;

/**
 * @author shiyuquan
 * Create Time: 2019/6/24 17:53
 */
public class FastjsonHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(FastjsonHelper.class);

    /**
     * 将对象转换为json
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error(String.format("obj=[%s]", obj.toString()), e);
        }
        return null;
    }

    /**
     * 将json转换为对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error(String.format("json=[%s]", json), e);
        }
        return null;
    }

    public boolean isJson(String str) {
        try {
            JSONObject.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isJsonArray(String str) {
        try {
            JSONArray.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
