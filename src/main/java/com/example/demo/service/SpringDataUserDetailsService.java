package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YanQin
 * @version v1.0.0
 * @Description : TODO
 * @Create on : 2020/9/20 00:02
 **/
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username:"+username);
        UserDetails userDetails=null;
        UserDto userDto = userDao.getUserByName(username);
        if(userDto == null){
            //查不到用户就返回null 由provider来抛出异常
            return null;
        }
        //根据用户id查询用户权限
        List<String> permissionsByUserId = userDao.findPermissionsByUserId(userDto.getId());
        //转成数组
        String[] permissionsArray=new String[permissionsByUserId.size()];
        permissionsByUserId.toArray(permissionsArray);
        //拿到数据库的数据与前端传过来的数据进行认证与授权比对
        userDetails= User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(permissionsArray).build();
        return userDetails;
    }
}
