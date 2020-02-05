package cn.zty.demo.dto;

import lombok.Data;

@Data
public class QuestionQuryDTO {
    private String search;
    private int page;
    private int size;
}
