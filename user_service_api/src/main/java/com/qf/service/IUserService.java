package com.qf.service;

import com.qf.entity.User;

public interface IUserService {
    public int addUser(User user);
    public User getUserByName(String userName);
    public int updateUserById(User user);
    public User getUserById(int id);
    public boolean userLogin(User user);
}
