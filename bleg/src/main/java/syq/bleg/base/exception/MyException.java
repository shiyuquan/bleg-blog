package syq.bleg.base.exception;

public class MyException extends RuntimeException {

    private int code;
    private String msg;
    private Object data;

    public MyException() {
        super();
        this.printStackTrace();
    }

    public MyException(int code) {
        super();
        this.code = code;
        this.msg = "";
        this.data = null;
        this.printStackTrace();
    }

    public MyException(String msg) {
        super(msg);
        this.code = 500;
        this.msg = msg;
        this.data = null;
        this.printStackTrace();
    }

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = null;
        this.printStackTrace();
    }

    public MyException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.printStackTrace();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
