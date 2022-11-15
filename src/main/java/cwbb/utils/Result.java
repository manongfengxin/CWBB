package cwbb.utils;

import lombok.Data;

@Data
public class Result {

    private boolean flag;

    private String msg;

    private Object data;


    public Result() {
    }

    public Result(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public Result(boolean flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }


    public static Result success(String msg) {
        return new Result(true, msg);
    }

    public static Result success(String msg, Object data) {
        return new Result(true,msg,data);
    }

    public static Result fail(String msg) {
        return new Result(false, msg);
    }

    public static Result fail(String msg, Object data) {
        return new Result(false,msg,data);
    }

}
