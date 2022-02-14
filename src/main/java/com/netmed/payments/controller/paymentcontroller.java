package com.netmed.payments.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netmed.payments.dao.MyordeRepo;
import com.netmed.payments.entities.MyOrder;
import com.razorpay.*;
//import com.razorpay.RazorpayClient;;

@Controller
public class paymentcontroller{
	@Autowired
	private MyordeRepo myorderRepo;
	
	@RequestMapping("/payment")
	public String pay() {
		
		return "payment";
	}
	
	@PostMapping("/create_order")
	@ResponseBody
	public String createorder(@RequestBody Map<String ,Object> data)throws Exception {
	    
	    int amt=Integer.parseInt(data.get("amount").toString());
	    System.out.println(data);
	    
	    var client=new RazorpayClient("rzp_test_G88oh2KCNH7FQZ", "9b6wZZ4d2jFPWTWRWBj0hdvW");
	    
	    JSONObject ob = new JSONObject();
	    ob.put("amount", amt*100);
	    ob.put("currency", "INR");
	    ob.put("receipt", "txn_123456");
	    
	    //creating new order
	    Order order = client.Orders.create(ob);
	    
	    System.out.println(order);
	    
	    //save this to DataBase
	    
	    MyOrder myorder=new MyOrder();
	    
	    myorder.setAmount(order.get("amount")+"");
	    myorder.setOrderid(order.get("id"));
	    myorder.setPaymentid(null);
	    myorder.setStatus("Created");
	    myorder.setUserid("Sambhu");
	    myorder.setReceipt(order.get("receipt"));
	    
	    this.myorderRepo.save(myorder);
	    
	    
	    
		return order.toString();
	}
	
	@PostMapping("/update_order")
	public ResponseEntity<?> updateorder(@RequestBody Map<String,Object> data){
		MyOrder myorder=this.myorderRepo.findByOrderId(data.get("order_id").toString());
		myorder.setPaymentid(data.get("payment_id").toString());
		myorder.setStatus(data.get("status").toString());
		this.myorderRepo.save(myorder);
		
		System.out.println(data);
		return ResponseEntity.ok(Map.of("msg","updated"));
	}
	
	//get id 
	//update 
	
	
}