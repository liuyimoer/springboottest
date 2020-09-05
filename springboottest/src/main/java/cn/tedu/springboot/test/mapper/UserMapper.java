package cn.tedu.springboot.test.mapper;

import cn.tedu.springboot.test.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper
 */
@Repository
public interface UserMapper {

    /**
     * 查询用户名称是否有重复
     *
     * @param user 参数
     * @return 返回结果
     */
//    @Select("SELECT username FROM t_user WHERE username = #{username} AND password = #{password}")
    @Select("SELECT username FROM t_user WHERE username = #{username}")
    String getRegUser(User user);

    /**
     * 新增用户信息
     *
     * @param user 参数
     */
    void insertReg(User user);

    /**
     * 根据用户名密码查询数据
     *
     * @param user 参数
     * @return 返回结果
     */
    @Select("SELECT username FROM t_user WHERE username = #{username} AND password = #{password}")
    String getLoginUser(User user);

    List<User> getUserInfo(@Param("id") Integer id, @Param("username") String username);

}
