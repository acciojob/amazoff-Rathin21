package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    
    

    HashMap<String,Order> orderMap = new HashMap<>();
    HashMap<String,DeliveryPartner> DPMap = new HashMap<>();
    HashMap<String, String> orderPair = new HashMap<>();
    HashMap<String,List<String>> orderList = new HashMap<>();
    
//1
    public void addOrder(Order order){

        orderMap.put(order.getId(),order);
    }

    //2
    public void addPartner(String partnerId){

        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        DPMap.put(partnerId, deliveryPartner);
    }

    //3
    public void addOrderPartnerPair(String orderId, String partnerId){

        //This is basically assigning that order to that partnerId
        orderPair.put(orderId, partnerId);

        if(!orderList.containsKey(partnerId)){
            orderList.put(partnerId, new ArrayList<>());
        }
        orderList.get(partnerId).add(orderId);
    }

    //4
    public Order getOrderById(String orderId){

        if(orderMap.containsKey(orderId))
            return orderMap.get(orderId);
        
        return null;
    }

   //5
    public DeliveryPartner getPartnerById(String partnerId){

        if(DPMap.containsKey(partnerId)){
            DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);

            if(orderList.containsKey(partnerId)){
                deliveryPartner.setNumberOfOrders(orderList.get(partnerId).size());
            }
            return deliveryPartner;
        }
        return null;
    }

   //6
    public int getOrderCountByPartnerId(String partnerId){

        if(orderList.containsKey(partnerId)){
            return orderList.get(partnerId).size();
        }
        return 0;
    }

    //7
    public List<String> getOrdersByPartnerId(String partnerId){
        
        if(orderList.containsKey(partnerId)){
            return orderList.get(partnerId);
        }
        return null;
    }

    //8
    public List<String> getAllOrders(){
        
        return new ArrayList<>(orderMap.keySet());
    }

    //9
    public int getCountOfUnassignedOrders(){
        
        int count=0;
        for(String orderId : orderMap.keySet()){
            if(!orderPair.containsKey(orderId))
                count++;
        }
        return count;
    }

    //10
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){

        if(!orderList.containsKey(partnerId)){
            return 0;
        }
        int giventime = TimeConverter.convertTimeStringtoInt(time);
        List<String> orders = orderList.get(partnerId);
        int orderLeft = 0;
        for(String id : orders){
            if(orderMap.get(id).getDeliveryTime()>giventime)
                orderLeft++;
        } 
        return orderLeft;
    }

    //11
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        
        if(!orderList.containsKey(partnerId))
            return null;
        
        List<String> orders = orderList.get(partnerId);
        int time = 0;

        for(String id : orders){
            int currTime = orderMap.get(id).getDeliveryTime();
            if(time<currTime){
                time=currTime;
            }
        }
        return TimeConverter.convertTimeInttoString(time);
    }

    //12
    public void deletePartnerById(String partnerId){

        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.
        DPMap.remove(partnerId);

        List<String> orders = orderList.get(partnerId);

        orderList.remove(partnerId);

        for(String id : orders){
            orderPair.remove(id);
        }
       
    }

    //13
    public void deleteOrderById(String orderId){

        if(!orderMap.containsKey(orderId))
            return;
        
        orderMap.remove(orderId);

        if(!orderPair.containsKey(orderId))
            return;
        
        String partnerId = orderPair.get(orderId);

        List<String> orders = orderList.get(partnerId);

        for(int i=0;i<orders.size();i++){

            if(orders.get(i).equals(orderId)){
                orders.remove(i);
                break;
            }
        }
        orderList.put(partnerId,orders);

    
    }

}
