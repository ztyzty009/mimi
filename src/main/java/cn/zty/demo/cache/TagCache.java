package cn.zty.demo.cache;

import cn.zty.demo.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO>get(){
        List<TagDTO> tagDTO = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","css","html","java","node.js","python","c++","c","golang","typescript","js","php","css","html","java","node.js","python","c++","c","golang","typescript"));
        tagDTO.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","django","yii"));
        tagDTO.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","apache"));
        tagDTO.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","sql","nosql","orcle"));
        tagDTO.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","github","maven"));
        tagDTO.add(tool);


        return tagDTO;
    }
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags,",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag ->tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}