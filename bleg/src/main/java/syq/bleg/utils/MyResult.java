package syq.bleg.utils;

public class MyResult<T> implements Result<T> {
    private Integer code;
    private String msg;
    private T data;

    @Override
    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MyResult(T data, Integer code, String msg ) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public MyResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyResult() {
    }

    public MyResult<T> success(T data, Integer code, String msg) {
        this.setData(data);
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }

    public MyResult<T> error(T data, Integer code, String msg) {
        this.setData(data);
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }
}
