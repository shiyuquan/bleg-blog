package syq.bleg.base.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import syq.bleg.base.exception.MyException;
import syq.bleg.base.MyResult;

/**
 * @author shiyuquan
 * Create Time: 2019/5/16 21:15
 */
@RestControllerAdvice
public class GobalExceptionHandler {

    /**
     * 封装异常抛出内容
     * @param e 异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public MyResult exceptionHandle(Exception e) {

        MyResult result = new MyResult();
        if (e instanceof MyException) {
            result.setCode(((MyException) e).getCode());
            result.setMsg(((MyException) e).getMsg());
            result.setData(((MyException) e).getData());
        } else {
            result.setCode(500);
            result.setMsg(e.getMessage());
            result.setData(e.getStackTrace());
        }
        e.printStackTrace();
        return result;
    }

}
