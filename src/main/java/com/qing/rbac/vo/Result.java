package com.qing.rbac.vo;

import lombok.Data;

@Data
public class Result {

    private Boolean success = true;
    private String msg;

    public Result() {

    }
    public Result(String msg) {
        this.msg = msg;
    }

    public static Result success() {
        return new Result();
    }

    public static Result error(String msg) {
        Result result = new Result(msg);
        result.setSuccess(false);
        return result;
    }
}
