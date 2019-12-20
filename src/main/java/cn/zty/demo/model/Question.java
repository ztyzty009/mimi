package cn.zty.demo.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Question {
    @Id//这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String tag;
    @Column
    private Long gmtCreate;
    @Column
    private Long gmtModified;
    @Column
    private Integer creator;
    @Column
    private Integer viewCount;
    @Column
    private Integer commentCount;
    @Column
    private Integer likeCount;


}
