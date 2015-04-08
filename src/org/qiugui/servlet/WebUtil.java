 package org.qiugui.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

import cn.edu.qiugui.service.IUserService;
import cn.edu.qiugui.service.LicenseInfo;
import cn.edu.qiugui.service.User;
 public class WebUtil {
	 
	 private static String ns="http://service.qiugui.edu.cn/";
	 
	 public static void addLicenseHeader(IUserService port,HttpServletRequest request){
		try {
			//1.将一个对象转换为xml 通过JAXB
				JAXBContext ctx = JAXBContext.newInstance(LicenseInfo.class);
				User ru = (User) request.getSession().getAttribute("loginUser");
				
				if (ru == null) return;
				
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
		} catch (PropertyException e) {
			 e.printStackTrace();
			 
		} catch (JAXBException e) {
			 e.printStackTrace();
			 
		} catch (ParserConfigurationException e) {
			 e.printStackTrace();
			 
		}
	 }
}

 