 package org.qiugui.Test;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import org.junit.Before;

import cn.edu.qiugui.service.IUserService;
import cn.edu.qiugui.service.User;
import cn.edu.qiugui.service.UserException_Exception;
import cn.edu.qiugui.service.UserService;

 public class Test {

	 private IUserService port;
	 private UserService us;
	 private String ns="http://service.qiugui.edu.cn/";
	 
	 @Before
	 public void init(){
		 try {
			 URL url = new URL("http://localhost:9090/ws_soa/tc?wsdl");
			 QName qName = new QName(ns, "UserService");
			 us = new UserService(url,qName);
			 port = us.getUserServicePort();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 @org.junit.Test
	 public void testList(){
		 List<User> users = port.list();
		 
		 for (User user : users) {
			System.out.println(user.getNickname()+user.getPassword());
		}
	 }
	 
	 @org.junit.Test
	 public void testAdd(){
		 User user = new User();
		 user.setUsername("zs");
		 user.setPassword("123");
		 user.setNickname("张三");
		 try {
			port.add(user);
		} catch (UserException_Exception e) {

			System.out.println(e.getMessage());
			 
		}
	 }
	 
	 @org.junit.Test
	 public void testDelete(){
		 String username = "zs";
		 port.delete(username);
	 }
	 
	 @org.junit.Test
	 public void testLogin(){
		
		try {
			 User user = port.login("zs", "123");
			 System.out.println(user.getUsername());
		} catch (UserException_Exception e) {
			System.out.println(e.getMessage());
			 
		}

		 
	 }
}

 