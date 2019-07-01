package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return  userMapper.insert(user);
    }

    @Override
    public User getUserByName(String userName) {
        Map<String,Object> map = new HashMap<>();
        map.put("user_name",userName);
        List<User> users = userMapper.selectByMap(map);
        if (users.isEmpty())
        {
            return null;
        }
        return users.get(0);
    }

    @Override
    public int updateUserById(User user) {
        User oldUser = userMapper.selectById(user.getId());
        oldUser.setPassword(user.getPassword());
        int result = userMapper.updateById(oldUser);
        return result;
    }

    @Override
    public User getUserById(int id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public boolean userLogin(User user) {
        Map<String,Object> map = new HashMap<>();
        map.put("user_name",user.getUserName());
        map.put("password",user.getPassword());
        if (userMapper.selectByMap(map).get(0)!=null){
            return true;
        }
        return false;
    }
}
