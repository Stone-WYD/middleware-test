package com.wyd.common;



public class AjaxResult<T> {

    private T data;

    private int code;

    private String message;


    public AjaxResult(int code, String name) {
        AjaxResult<?> result = new AjaxResult<>();
        result.setMessage(name);
        result.setCode(code);
    }

    public static <T> AjaxResult<T> getTrueAjaxResult(T data) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(200);
        result.setMessage("成功！");
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AjaxResult() {
    }

    public AjaxResult(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
