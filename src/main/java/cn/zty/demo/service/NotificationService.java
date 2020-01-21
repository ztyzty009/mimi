package cn.zty.demo.service;

import cn.zty.demo.dto.NotificationDTO;
import cn.zty.demo.dto.PaginationDTO;
import cn.zty.demo.enums.NotificationStatusEnum;
import cn.zty.demo.enums.NotificationTypeEnum;
import cn.zty.demo.exception.CustomizeEception;
import cn.zty.demo.exception.CustomizeErrorCode;
import cn.zty.demo.mapper.NotificationMapper;
import cn.zty.demo.model.Notification;
import cn.zty.demo.model.NotificationExample;
import cn.zty.demo.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;


    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
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

        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        if (notifications.size() ==0){
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        for (Notification notification:notifications){
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfTyoe(notification.getType()));
            notificationDTOs.add(notificationDTO);
        }
        paginationDTO.setDate(notificationDTOs);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(example);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null){
            throw new CustomizeEception(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())){
            throw new CustomizeEception(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);


        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfTyoe(notification.getType()));
        return notificationDTO;
    }
}
