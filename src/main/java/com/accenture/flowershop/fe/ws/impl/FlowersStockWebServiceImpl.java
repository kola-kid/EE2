/*
package com.accenture.flowershop.fe.ws.impl;


import com.accenture.flowershop.be.business.order.CatalogBusinessService;
import com.accenture.flowershop.fe.ws.FlowersStockWebService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.jws.WebService;


@WebService(endpointInterface = "com.accenture.flowershop.fe.ws.FlowersStockWebService")
public class FlowersStockWebServiceImpl implements  {

    @Autowired
    private CatalogBusinessService catalogBusinessService;


    public FlowersStockWebServiceImpl() {
    }


    public void increaseFlowersStockSize( int count){
       catalogBusinessService.addCountFlower(count);
  }

    @PostConstruct
    public void test(){
        System.out.println("FlowersStockWebServiceImpl создан! ");
    }
}
*/
