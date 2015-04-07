 package org.qiugui.Test;

import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.w3c.dom.Document;

import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

import cn.edu.qiugui.service.IUserService;
import cn.edu.qiugui.service.LicenseInfo;
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
		try {
				
			//1.将一个对象转换为xml 通过JAXB
			JAXBContext ctx = JAXBContext.newInstance(LicenseInfo.class);
			User ru = new User();
			ru.setUsername("admin");
			ru.setNickname("超级管理员" );
			ru.setPassword("123");
			 
			LicenseInfo info = new LicenseInfo();
			info.setRegisterUser(ru);
			
			//不直接传info对象给mars.marshal(),因为不在LicenseInfo注解会报如下错误
			//Caused by: com.sun.istack.SAXException2: 
			//由于类型 "cn.edu.qiugui.service.LicenseInfo" 缺少 @XmlRootElement 注释, 无法将该类型编集为元素
			//所以使用下面的方法，将info转化成JAXBElement，进行传
			QName name = new QName(ns,"licenseInfo");
			JAXBElement<LicenseInfo> element = new JAXBElement<LicenseInfo>(name, LicenseInfo.class, info);
			
			Marshaller mars = ctx.createMarshaller();
			mars.setProperty(Marshaller.JAXB_FRAGMENT, true);
			mars.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			
			//2.转换成dom对象
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		
			mars.marshal(element, doc);
			
			//3.同过Headers.create()完成header的添加
			WSBindingProvider wsb = (WSBindingProvider) port;
			wsb.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
				
			User user = new User();
			user.setUsername("zs");
			user.setPassword("123");
			user.setNickname("张三");
			port.add(user);
		} catch (JAXBException e) {
			 e.printStackTrace();
			 
		} catch (ParserConfigurationException e) {
			 e.printStackTrace();
			 
		} catch (UserException_Exception e) {
			 e.printStackTrace();
			 
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

 