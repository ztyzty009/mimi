package cn.zty.demo.repository;

import cn.zty.demo.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;



public interface UserRepository extends JpaRepository<User,Integer>{

    @Select(value = "select * from tb1_user where token=#{token}")
    User findByToken(@Param("token") String token);


}
