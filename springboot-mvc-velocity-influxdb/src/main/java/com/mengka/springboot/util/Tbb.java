package com.mengka.springboot.util;

import com.mengka.springboot.service.ManualServiceImpl;
import javax.xml.ws.Endpoint;

/**
 *  http://127.0.0.1:8991/cabbagepro/manualservice?wsdl
 *
 */
public class Tbb {

    public static void main(String[] args){
        String service = "http://localhost:8992/cabbagepro/manualservice";
        Endpoint.publish(service, new ManualServiceImpl());
    }
}
