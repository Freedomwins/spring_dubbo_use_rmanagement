package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendCodeEmail(Email email) {
        //创建一封邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            //创建一个Spring提供的邮件帮助对象
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            //设置发送方
            messageHelper.setFrom("zj490057768@sina.com");

            //设置接收方
            //to - 普通接收方
            //cc - 抄送方
            //bcc - 密送方
            messageHelper.addTo(email.getAddress(), "金牌会员");

            //设置标题

            messageHelper.setSubject("验证码！");

            //设置内容

            messageHelper.setText("验证码是："+email.getCode(), true);

            //设置时间
            messageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessage);

        }catch (MessagingException e){

        } catch (UnsupportedEncodingException e) {

        }
    }

    @Override
    public boolean sendFindPasswordeEmail(User user) {

        //创建一封邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            //创建一个Spring提供的邮件帮助对象
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            //设置发送方
            messageHelper.setFrom("zj490057768@sina.com");

            //设置接收方
            //to - 普通接收方
            //cc - 抄送方
            //bcc - 密送方
            messageHelper.addTo(user.getEmail(), "金牌会员");

            //设置标题

            messageHelper.setSubject("找回密码！");

            //设置内容
            messageHelper.setText("请点击<a href='localhost:8080/user/toResetPassword?userName="+user.getUserName()+"'>这里</a>找回密码~", true);

            //设置时间
            messageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessage);

            return true;
        }catch (MessagingException e){
            return false;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }
}
