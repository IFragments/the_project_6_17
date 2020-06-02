package com.cl.data;

import java.io.Serializable;

/**
 * Created by 任小龙 on 2020/6/2.
 */
public class BaseInfo<D> implements Serializable {
    private static final long serialVersionUID = 397353268011486314L;
    public int errNo;
    public int exeTime;
    public String msg;
    public D result;

    public boolean isSuccess() {
        return errNo == 0;
    }
}
