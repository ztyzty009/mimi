package cn.zty.demo.repository;

import cn.zty.demo.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question,Integer> {








}
