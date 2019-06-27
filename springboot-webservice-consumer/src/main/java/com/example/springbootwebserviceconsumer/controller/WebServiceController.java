package com.example.springbootwebserviceconsumer.controller;

import com.example.springbootwebserviceconsumer.utils.JsonHelper;
import com.example.springbootwebserviceconsumer.utils.WsUtil;
import org.apache.cxf.endpoint.Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

@RestController
public class WebServiceController {
    /**
     * 动态调用
     * 
     * @return java.lang.String
     * @Author luopf 2019/6/27 11:14
     */
    @RequestMapping(value = "/hello1")
    public String hello1() {
        JaxWsDynamicClientFactory dcflient = JaxWsDynamicClientFactory.newInstance();

        Client client = dcflient.createClient("http://localhost:8080/service/user?wsdl");
	    String jsonStr = null;
        Object[] objects = null;
        try {
            objects = client.invoke("getUser", "411001");
	        jsonStr = JsonHelper.getInstance().writeValueAsString(objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonStr;
    }

    @RequestMapping(value = "/hello2")
    public String hello2() {
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient("http://localhost:8080/service/user?wsdl");
        QName opName = WsUtil.getOperationName(client.getEndpoint(), "getUser");
        Object[] result = null;
        String jsonStr = null;
        try {
            result = client.invoke(opName, "411001");
            // 如果返回的是一个对象，可以转换成json取值比较方便，也可以通过反射取值result[0].getClass()，比较麻烦
            jsonStr = JsonHelper.getInstance().writeValueAsString(result[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

}
