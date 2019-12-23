package cn.zty.demo.exception;

public class CustomizeEception extends RuntimeException{
    private String message;

    public CustomizeEception(ICustomizeErrorCode errorCode) {
        this.message=errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
