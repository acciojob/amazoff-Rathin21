package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;
    //1
    public void addOrder(Order order){

        orderRepository.addOrder(order);
    }

    //2
    public void addPartner(String partnerId){

        orderRepository.addPartner(partnerId);
    }

    //3
    public void addOrderPartnerPair(String orderId, String partnerId){

        orderRepository.addOrderPartnerPair(orderId, partnerId);
    }

    //4
    public Order getOrderById(String orderId){

        return orderRepository.getOrderById(orderId);
    }

   //5
    public DeliveryPartner getPartnerById(String partnerId){

        return orderRepository.getPartnerById(partnerId);
    }

   //6
    public int getOrderCountByPartnerId(String partnerId){

        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    //7
    public List<String> getOrdersByPartnerId(String partnerId){
        
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    //8
    public List<String> getAllOrders(){
        
        return orderRepository.getAllOrders();
    }

    //9
    public int getCountOfUnassignedOrders(){
        
        return orderRepository.getCountOfUnassignedOrders();
    }

    //10
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){

        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    //11
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    //12
    public void deletePartnerById(String partnerId){

        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){

        orderRepository.deleteOrderById(orderId);
    
    }
}
