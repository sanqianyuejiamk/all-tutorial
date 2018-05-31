package com.mengka.springboot.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *  SOAP Binding: Difference between Document and RPC Style Web Services
 *  https://java.globinch.com/enterprise-java/web-services/soap-binding-document-rpc-style-web-services-difference/
 *
 *  @SOAPBinding(style = Style.RPC)
 */
@WebService
public interface ManualService {
    @WebMethod
    public int arrival(int regionCode, String berthCode);

    @WebMethod
    public int departure(int regionCode, String berthCode);
}
