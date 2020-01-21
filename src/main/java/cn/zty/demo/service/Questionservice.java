package cn.zty.demo.service;

import cn.zty.demo.dto.PaginationDTO;
import cn.zty.demo.dto.QuestionDTO;
import cn.zty.demo.exception.CustomizeEception;
import cn.zty.demo.exception.CustomizeErrorCode;
import cn.zty.demo.mapper.QuestionExtMapper;
import cn.zty.demo.mapper.QuestionMapper;
import cn.zty.demo.mapper.UserMapper;
import cn.zty.demo.model.Question;
import cn.zty.demo.model.QuestionExample;
import cn.zty.demo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Questionservice {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page <= 1) {
            page = 1;
        }
        if (page > totalPage && totalPage > 0) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setDate(questionDTOList);


        return paginationDTO;
    }

    public PaginationDTO list(long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(example);
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page <= 1) {
            page = 1;
        }
        if (page > totalPage && totalPage > 0) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setDate(questionDTOList);


        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question==null){
            throw new CustomizeEception(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;


    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insertSelective(question);
        } else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (update!=1){
                throw new CustomizeEception(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);

    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }

        String tags = StringUtils.replace(queryDTO.getTag(), ",", "|");
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(tags);
        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q ->{
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());

        return questionDTOS;
    }
}
