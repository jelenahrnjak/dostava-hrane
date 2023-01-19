import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../../services/restaurant.service'
import { OrderService } from '../../services/order.service'
import { Restaurant } from '../../model/restaurant.model' 
import { ActivatedRoute, Router } from '@angular/router';
import { OrderItem } from '../../model/orderItem.model';
import { ToastrService } from 'ngx-toastr';   

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.scss']
})
export class RestaurantComponent implements OnInit {

  restaurant : any;
  restId : any = '';
  meals : any;
  role : any = sessionStorage.getItem('role');

  order: any ={
    "orderId" : "",
    "customerId" : "",
    "restaurantId" : "",
    "totalPrice" : 0,
    "orderStatus" : "CREATED",
    "orderItems" : []
    

  };  
  
  constructor(
    private toastr: ToastrService,  
    private router: Router, private restaurantService : RestaurantService,
    private route: ActivatedRoute,
    private orderService : OrderService) { }

  ngOnInit(): void {

    this.order.orderItems = []
    
    this.restId = this.route.snapshot.paramMap.get('id');
    this.restaurantService.findById(this.restId).subscribe((data : Restaurant) => {
      this.restaurant = data;
      this.meals = data.meals
      this.meals.forEach((meal : OrderItem)=> {
        meal.itemCount = 0;
      });
      console.dir(this.meals)
    }); 


  }

  changed(item : any, count : number){

    this.meals.forEach((element : OrderItem) => {
      if(element.mealId.match(item.mealId) && count < 0){
        element.itemCount = 0;
        return;
      }
    });
    
    var newItem : OrderItem = {
      "itemName" : item.name,
      "itemCount" : count,
      "itemPrice" : item.price * count,
      "mealId" : item.mealId,
      "mealType" : item.mealType
    }
    let found = 0; 
    this.order.totalPrice = 0
    this.order.orderItems.forEach((element : OrderItem,index : any) => {
      if(element.itemName.match(item.name)){
        console.dir(element)
        element = newItem;
        this.order.orderItems.splice(index,1)
        
      }else{
        this.order.totalPrice += element.itemPrice

      }
    });

    if(found ==  0 && count > 0){ 
      this.order.orderItems.push(newItem)
      
      this.order.totalPrice += newItem.itemPrice 
    } 

  }

  createOrder(){
    this.order.customerId = sessionStorage.getItem("userId")
    this.order.restaurantId = this.restId
    console.dir(this.order)

    this.orderService.createOrder(this.order)
    .subscribe(data => { 
      this.toastr.success('Nova porudžbina uspešno kreirana!')  
      this.router.navigate(['my-orders']);
    },
      error => { 
        console.log('Adding order error');  
        this.toastr.error(error['error'].message)
      });

  }

}
