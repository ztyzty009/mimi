package cn.zty.demo.mapper;

import cn.zty.demo.model.Question;
import cn.zty.demo.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
    int intCommentCount(Question record);
}