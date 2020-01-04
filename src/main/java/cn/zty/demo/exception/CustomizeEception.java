package cn.zty.demo.exception;

public class CustomizeEception extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeEception(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message=errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
