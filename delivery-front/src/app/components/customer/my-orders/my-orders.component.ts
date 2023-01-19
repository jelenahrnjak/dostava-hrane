import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../../services/order.service';
import { RestaurantService } from '../../../services/restaurant.service';
import { Order } from '../../../model/order.model'
import { Restaurant } from '../../../model/restaurant.model';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.scss']
})
export class MyOrdersComponent implements OnInit {



  activeOrders : Order[] = [] 
  currentOrder : any = ""
  display = 'none'
  show = false;
  
  
  constructor(
    // private toastr: ToastrService, 
    private orderService: OrderService,
    private restaurantService : RestaurantService,
    ) { }

  ngOnInit(): void {
    this.getAllActiveOrders() 
    

    setTimeout(() => {this.getRestaurants()}, 200);   
  }

  getRestaurants(){ 
    this.activeOrders.forEach((element : Order, index : any) => {

      this.restaurantService.findById(element.restaurantId).subscribe((data : Restaurant) => {
        this.activeOrders[index].restaurant = data;
      })
    });
 
    console.dir(this.activeOrders) 
    this.show= true
     
  }
  selectOrder(order : any){
    this.currentOrder = order
    this.display = 'display'
  }

  getAllActiveOrders(){
    
    this.activeOrders = []
    const userId = sessionStorage.getItem('userId')
    this.orderService.findAllByCustomer(userId).subscribe((data : Order[]) => {
      this.activeOrders = data;
    }); 
  }
   
  cancelOrder(orderId : string){ 
    this.orderService.cancelOrder(orderId).subscribe(); 
  }


}
