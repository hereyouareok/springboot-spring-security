package com.example.demo.dao;

import com.example.demo.model.PermissionDto;
import com.example.demo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YanQin
 * @version v1.0.0
 * @Description : TODO
 * @Create on : 2020/9/20 15:33
 **/
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDto getUserByName(String userName) {
        String sql = "select id,username,password,fullname,mobile from t_user where username=?";
        List<UserDto> userDtos = jdbcTemplate.query(sql, new Object[]{userName}, new
                BeanPropertyRowMapper<>(UserDto.class));
        if (userDtos != null && userDtos.size() >= 1) {
            return userDtos.get(0);
        }
        return null;

    }

    //根据用户id查询用户权限
    public List<String> findPermissionsByUserId(String userId) {
        String sql = "select * from t_permission where id in(select permission_id from t_role_permission where role_id in(select role_id from t_user_role where user_id=?))";
        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId}, new
                BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>();
        list.forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }
}
