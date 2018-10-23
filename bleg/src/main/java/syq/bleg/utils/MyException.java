package syq.bleg.utils;

public class MyException extends Exception {
    private int code;

    public MyException() {
        super();
    }

    public MyException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
