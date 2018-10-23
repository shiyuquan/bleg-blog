package syq.bleg.utils;

import java.io.Serializable;

public interface Result<T> extends Serializable {

    Integer getCode();
    String getMsg();
    T getData();
    default String string() { return "{code:" + this.getCode() + ",msg:" +this.getMsg()+ "}";}
}
