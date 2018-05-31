package com.mengka.springboot.util;

import com.mengka.springboot.service.ManualService;

import javax.xml.namespace.QName;
import java.net.URL;
import javax.xml.ws.Service;

/**
 *  https://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example/
 */
public class client_01 {

    public static void main(String[] args)throws Exception{
        URL url = new URL("http://localhost:8992/cabbagepro/manualservice?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://service.springboot.mengka.com/", "ManualServiceImplService");

        Service service = Service.create(url, qname);

        ManualService manualService = service.getPort(ManualService.class);
        manualService.arrival(1,"22");
    }
}
