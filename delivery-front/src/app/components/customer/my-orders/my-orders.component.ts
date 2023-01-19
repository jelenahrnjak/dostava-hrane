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
  
  
  constructor(
    // private toastr: ToastrService, 
    private orderService: OrderService,
    private restaurantService : RestaurantService,
    ) { }

  ngOnInit(): void {
    this.getAllActiveOrders()

    var i = 0;
    this.activeOrders.forEach(element => {

      this.restaurantService.findById(element.restaurant).subscribe((data : Restaurant) => {
        this.activeOrders[i].restaurant = data;
      })
    });

    i++;
  }

  selectOrder(order : any){
    this.currentOrder = order
  }

  getAllActiveOrders(){
    
    this.activeOrders = []
    const userId = sessionStorage.getItem('userId')
    this.orderService.findAllByCustomer(userId).subscribe((data : Order[]) => {
      this.activeOrders = data;
    }); 
  }
   
  cancelOrder(orderId : string){
    alert(orderId)
  }


}
