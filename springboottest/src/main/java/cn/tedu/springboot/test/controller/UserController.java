package cn.tedu.springboot.test.controller;

import cn.tedu.springboot.test.entity.User;
import cn.tedu.springboot.test.mapper.UserMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户Controller
 */
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param user 参数信息
     * @return 返回结果
     * @throws Exception 异常信息
     */
    @RequestMapping("/reg")
    public String reg(User user/*, HttpServletResponse response*/) throws Exception {
        if(StringUtils.isEmpty(user.getUsername())){
//            throw new Exception("用户名为空");
            return "用户名不能为空";
        }
        if(StringUtils.isEmpty(user.getPassword())){
//            throw new Exception("密码为空");
            return "密码不能为空";
        }
        try{
            String obj = userMapper.getRegUser(user);
            if(null != obj){
                return "注册失败 ，名称【" + obj + "】重复";
            }
            userMapper.insertReg(user);
//            response.sendRedirect("http://localhost/success.html");
//            response.setHeader("Refresh", "7;url=http://localhost/success.html");
            return "注册成功";

        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("注册失败");
        }
    }

    /**
     * 用户登录
     *
     * @param user 参数
     * @param session session
     * @return 返回结果
     */
    @RequestMapping("/login")
    public String login(User user, HttpSession session) throws Exception{
        try{
            String username = userMapper.getLoginUser(user);
            if(null != username){
                session.setAttribute("user",username);
                return "登录成功";
            }
            return "用户名或密码错误";
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("登录失败");
        }
    }

    @RequestMapping("/getUserInfo")
    public List<User> getUserInfo(Integer id,String um) throws Exception {
        //传入的id和um是什么类型
//        System.out.println(id+um);
        User userInfo = new User();
//        if(null == id && StringUtils.isEmpty(um)){
//            user.setMessage("至少输入id或用户名进行查询");
//        }
        List<User> result = new ArrayList<>();
        try{
            List<User> data = userMapper.getUserInfo(id,um);
            if(data.size() == 0){
                userInfo.setMessage("未查询出用户信息");
                result.add(userInfo);
                return result;
            }

            for(User obj : data){
                User user = new User();
                user.setId(obj.getId());
                user.setUsername(obj.getUsername());
                user.setPassword(obj.getPassword());
                user.setAge(obj.getAge());
                user.setPhone(obj.getPhone());
                user.setEmail(obj.getEmail());
                result.add(user);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("获取信息失败");
        }
    }

}
