package cn.zty.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{


    QUESTION_NOT_FOUND(2001,"你找的问题不存在或已被删除" ),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复" ),
    NO_LOGIN(2003,"当前操作需要登录，请先登录" ),
    SYS_ERROR(2004,"服务器冒烟了，要不稍后再试试！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"兄弟你这是读别人的信息呢?"),
    NOTIFICATION_NOT_FOUND(2009,"消息莫非是鼻翼而非了？"),
    UPLOAD_IMAGE_TOO_BIG(2010,"图片太大"),
    IMAGE_UPLOAD_FAILED(2011,"上传图片有问题");

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }



    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
