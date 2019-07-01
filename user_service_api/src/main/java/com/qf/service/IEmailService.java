package com.qf.service;

import com.qf.entity.Email;
import com.qf.entity.User;

import java.io.UnsupportedEncodingException;

public interface IEmailService {
    public void sendCodeEmail(Email email);

    public boolean sendFindPasswordeEmail(User user);
}
