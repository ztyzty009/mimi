package cn.zty.demo.model;

import lombok.Data;

import javax.persistence.*;

//使用jpa注解配置映射关系
@Data
@Entity //告诉jap这是一个实体类(和数据表映射的实体类)
@Table(name = "tb1_user") //指定和那个数据表对应，如果省略默认是类名小写
public class User {
    @Id//这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    @Column
    private String name;
    @Column
    private String accountId;
    @Column
    private String token;
    @Column
    private Long gmtCreate;
    @Column
    private Long gmtModified;
    @Column
    private String avatarUrl;

}
