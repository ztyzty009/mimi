package cn.zty.demo.dto;

import cn.zty.demo.exception.CustomizeEception;
import cn.zty.demo.exception.CustomizeErrorCode;
import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);

        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("成功");
        return resultDTO;
    }
    public static<T> ResultDTO okOf(T t){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("成功");
        resultDTO.setData(t);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeEception e) {

        return errorOf(e.getCode(),e.getMessage());
    }
}
