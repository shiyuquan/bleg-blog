package syq.bleg.sample.user;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import syq.bleg.base.MyResult;
import syq.bleg.base.db.redis.RedisService;
import syq.bleg.base.exception.MyException;
import syq.bleg.constants.StreamConstant;
import syq.bleg.io.DefaultStreamConverter;
import syq.bleg.utils.FileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

/**
 * @Author shiyuquan
 * @Date 2018/10/11 13:49
 * @Description TODO
 */
@RestController
@RequestMapping("/api")
@Api(description = "用户  Controller")
public class UserController {
    private UserService userService;
    RedisService redisService;
    DefaultStreamConverter streamConverter;

    @Resource
    public void setStreamConverter(DefaultStreamConverter streamConverter) {
        this.streamConverter = streamConverter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Value("${web.upload-path}")
    private String imgPath;

    @PostMapping("/register")
    public MyResult<User> addUser(@RequestBody User user, @RequestParam String checkNum) {
        if (StringUtils.isEmpty(user.getUserName())) {
            return new MyResult<User>().error(null, 10001, "请输入用户名");
        } else {
            if(userService.findUser(user.getUserName()) != null) {
                return new MyResult<User>().error(null, 10002, "用户名已存在，请重新输入");
            }
        }
        if (StringUtils.isEmpty(user.getNickName())) {
            return new MyResult<User>().error(null, 10003, "请输入昵称");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return new MyResult<User>().error(null, 10005, "请输入密码");
        }
        if (StringUtils.isEmpty(user.getPhone())) {
            return new MyResult<User>().error(null, 10004, "请输入手机号");
        }
//        if (StringUtils.isEmpty(checkNum)) {
//            return new MyResult<User>().error(null, 10006, "请输入验证码");
//        } else {
//            String code = (String)redisService.get(user.getPhoneNum());
//            if(StringUtils.isEmpty(code)) {
//                return new MyResult<User>().error(null, 10007, "验证码不存在");
//            }
//            if (!checkNum.equals(code)) {
//                return new MyResult<User>().error(null, 10008, "验证码错误");
//            }
//        }
        user.setId(UUID.randomUUID().toString());
        user.setRegisterTime(new Date());
        User user1 = userService.saveUser(user);
        if(user1 == null) {
            return new MyResult<User>().error(user1, 10009, "新增失败");
        }
        return new MyResult<User>().success(user1, 200, "success");
    }

    @GetMapping(value = "/login")
    public MyResult<User> login(@RequestParam String userName, @RequestParam String password ) {
        if(StringUtils.isEmpty(userName) ) {
            return new MyResult<User>().error(null, 10001, "请输入用户名");
        }
        if(StringUtils.isEmpty(password)) {
            return new MyResult<User>().error(null, 10005, "请输入密码");
        }
        User user = userService.findUser(userName);
        if(null == user) {
            return new MyResult<User>().error(null, 10010, "用户不存在");
        }
        user = userService.findUser(userName, password);
        if(null == user) {
            return new MyResult<User>().error(null, 10011, "密码错误");
        }
        return new MyResult<User>().success(user, 200, "success");
    }

    @PostMapping(value = "/upload-profilePicture")
    public MyResult<User> upload(@RequestParam("file")MultipartFile file,
                         @RequestParam("userName")String userName,
                         @RequestParam("password")String password,
                         HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            return new MyResult<User>().error(null, 10012, "文件为空");
        }
        User user = userService.findUser(userName, password);
        String id = user.getId();
        String path = imgPath + id;
        String imgPath = FileUtil.saveImg(file, path);
        user.setProfilePicture(imgPath);
        User user1 = userService.saveUser(user);
        return new MyResult<User>().error(user1, 200, "success");
    }

    @PutMapping(value = "/user")
    public MyResult<User> updateUser(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUserName())) {
            return new MyResult<User>().error(null, 10001, "请输入用户名");
        } else {
            if (userService.findByUserNameAndIdNotIn(user.getUserName(), user.getId()).size() != 0) {
                return new MyResult<User>().error(null, 10002, "用户名已存在，请重新输入");
            }
        }
        if (StringUtils.isEmpty(user.getNickName())) {
            return new MyResult<User>().error(null, 10003, "请输入昵称");
        }
        if (StringUtils.isEmpty(user.getPhone())) {
            return new MyResult<User>().error(null, 10004, "请输入手机号");
        }
        User user1 = userService.saveUser(user);
        if(user1 == null) {
            return new MyResult<User>().error(user1, 10009, "新增失败");
        }
        return new MyResult<User>().success(user1, 200, "success");
    }

    @GetMapping(value = "/export")
    public void exportTest(HttpServletRequest request, HttpServletResponse response) {
        String data = "im the tester data /*-+@#$%^&*()_+";
        // DefaultStreamConverter converter = new DefaultStreamConverter();
        try (OutputStream os = streamConverter.getEncodeStream(response.getOutputStream(), StreamConstant.BIZ_USER, StreamConstant.RULE_BASE64)) {
            setResponseHeader(request, response, "tester");
            os.write(data.getBytes());
            os.flush();
        } catch (IOException e) {
            throw new MyException("export error");
        }
    }

    @PostMapping(value = "/import")
    public MyResult<String> importTest(@RequestParam("file") MultipartFile file) {
        String data;
        if (isGmpFile(file.getOriginalFilename())) {
            try {
                InputStream is = streamConverter.getDecodeStream(file.getInputStream());
                int c = 0;
                StringBuilder sb = new StringBuilder();
                while ((c = is.read()) != -1) {
                    sb.append((char) c);
                }
                data = sb.toString();
            } catch (IOException e) {
                throw new MyException("import error");
            }
        } else {
            throw new MyException("is error file");
        }
        return new MyResult<String>().success(data, 200, "success");

    }

    /**
     * 判断文件名是否是.gmp
     * @param fileName 文件名
     * @return boolean
     */
    private static boolean isGmpFile(String fileName) {
        return fileName.endsWith(".bleg");
    }

    /**
     * 创建.gmp的文件名
     * @param name 无后缀的文件名
     * @return 带后缀的文件名
     */
    private static String createGmpFileName(String name) {
        return name + ".bleg";
    }

    /**
     * 设置header
     */
    private static void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        final String userAgen = request.getHeader("USER-AGENT");
        String finalFileName = null;

        try {
            if (org.apache.commons.lang3.StringUtils.contains(userAgen, "MSIE")) {
                finalFileName = URLEncoder.encode(fileName, "UTF8");
            } else if (org.apache.commons.lang3.StringUtils.contains(userAgen, "Mozilla")){
                finalFileName = new String(fileName.getBytes(), "ISO-8859-1");
            } else {
                finalFileName = URLEncoder.encode(fileName, "UTF8");
            }
        } catch (UnsupportedEncodingException e) {
            throw new MyException("response header set error");
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + finalFileName + ".bleg");
    }
}
