package cn.zty.demo.service;

import cn.zty.demo.dto.PaginationDTO;
import cn.zty.demo.dto.QuestionDTO;
import cn.zty.demo.mapper.QuestionMapper;
import cn.zty.demo.mapper.UserMapper;
import cn.zty.demo.model.Question;
import cn.zty.demo.model.User;
import cn.zty.demo.repository.QuestionRepository;
import cn.zty.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Questionservice {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    public List<QuestionDTO> list() {

        //Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        List<Question> questions = questionRepository.findAll();

        //PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question:questions){
            User user = userRepository.findById(question.getCreator()).get();
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        //paginationDTO.setQuestions(questionDTOList);

        Integer totalCount = questionMapper.totalcount();
        //paginationDTO.setPagination(totalCount,page,size);
        return questionDTOList;
    }
}
