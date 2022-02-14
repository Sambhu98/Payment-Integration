
const paymentStart = () =>{
    console.log("Payment Started ..");
    var x = document.getElementById("payment_field").value;
    console.log(x);
    
    if(x=="" || x==null)
    {
    swal("OOPS", "Amount Required", "error");
    return;
    }
    
    $.ajax({
    url:"/create_order",
    data:JSON.stringify({amount:x,info:'order_request'}),
    contentType:"application/json",
    type:"POST",
    dataType:"json",
    success:function(response){
    console.log(response);
    	if(response.status=="created"){
    	let options={
    	key:"rzp_test_G88oh2KCNH7FQZ",
    	amount:response.amount,
    	currency:"INR",
    	name:"Netmed",
    	description: "Checkout",
    	image:"https://www.netmeds.com/images/cms/wysiwyg/opengraph/logo-fb.png",
    	order_id:response.id,
    	
    	handler:function(response){
    	console.log(response.razorpay_payment_id)
    	console.log(response.razorpay_order_id)
    	console.log(response.razorpay_signature)
    	console.log('payment succesuful')
    	
    	updatepaymentOnserver(
    	response.razorpay_payment_id,
    	response.razorpay_order_id,
    	"paid"
    	);
    	
    	},
    	"prefill": { 
 		"name": "", 
 		"email": "", 
 		"contact": "" 
 		},
 		"notes": { 
 		"address": "Chennai,TamilNadu,India"
 		}, 
 		"theme": { 
 		"color": "#3399cc" 
 		} 	 
    	};
    	
		let rzp1 = new Razorpay(options); 
		rzp1.on('payment.failed', function (response){ 
		 console.log(response.error.code); 
		 console.log(response.error.description); 
		 console.log(response.error.source); 
		 console.log(response.error.step); 
		 console.log(response.error.reason); 
		 console.log(response.error.metadata.order_id); 
		 console.log(response.error.metadata.payment_id); 
		 swal("Failed!", "Payment failed", "error");
		}); 
		
		rzp1.open();
    	}
    },
    
    error:function(error){
    console.log(error)
    alert("something went wrong");
    },
    
    });
};

function updatepaymentOnserver(payment_id,order_id,status){
$.ajax({
	url:"/update_order",
    data:JSON.stringify({payment_id:payment_id,order_id:order_id,status:status}),
    contentType:"application/json",
    type:"POST",
    dataType:"json",
    success:function(response){
    swal("Good job!", "Payment Done", "success");
    },
    error:function(response){
    swal("Failed!", "not updated on server", "error");
    },
});
};