
package cn.edu.qiugui.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IUserService", targetNamespace = "http://service.qiugui.edu.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IUserService {


    /**
     * 
     * @param user
     * @throws UserException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "add", targetNamespace = "http://service.qiugui.edu.cn/", className = "cn.edu.qiugui.service.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://service.qiugui.edu.cn/", className = "cn.edu.qiugui.service.AddResponse")
    public void add(
        @WebParam(name = "user", targetNamespace = "")
        User user)
        throws UserException_Exception
    ;

    /**
     * 
     * @param username
     * @throws UserException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://service.qiugui.edu.cn/", className = "cn.edu.qiugui.service.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://service.qiugui.edu.cn/", className = "cn.edu.qiugui.service.DeleteResponse")
    public void delete(
        @WebParam(name = "username", targetNamespace = "")
        String username)
        throws UserException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<cn.edu.qiugui.service.User>
     */
    @WebMethod
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "list", targetNamespace = "http://service.qiugui.edu.cn/", className = "cn.edu.qiugui.service.List")
    @ResponseWrapper(localName = "listResponse", targetNamespace = "http://service.qiugui.edu.cn/", className = "cn.edu.qiugui.service.ListResponse")
    public List<User> list();

    /**
     * 
     * @param password
     * @param username
     * @return
     *     returns cn.edu.qiugui.service.User
     * @throws UserException_Exception
     */
    @WebMethod
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.qiugui.edu.cn/", className = "cn.edu.qiugui.service.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.qiugui.edu.cn/", className = "cn.edu.qiugui.service.LoginResponse")
    public User login(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws UserException_Exception
    ;

}
