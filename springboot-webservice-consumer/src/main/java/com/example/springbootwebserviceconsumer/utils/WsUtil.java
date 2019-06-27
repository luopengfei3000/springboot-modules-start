package com.example.springbootwebserviceconsumer.utils;

import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;

public class WsUtil {
    public WsUtil() {}

    public static QName getOperationName(Endpoint endpoint, String operation) {
        QName opName = new QName(endpoint.getService().getName().getNamespaceURI(), operation);
        BindingInfo bindingInfo = endpoint.getEndpointInfo().getBinding();
        if (bindingInfo.getOperation(opName) == null) {
            Iterator i$ = bindingInfo.getOperations().iterator();

            while (i$.hasNext()) {
                BindingOperationInfo operationInfo = (BindingOperationInfo)i$.next();
                if (operation.equals(operationInfo.getName().getLocalPart())) {
                    opName = operationInfo.getName();
                    break;
                }
            }
        }

        return opName;
    }
}