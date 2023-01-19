import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../../services/order.service';
import { UserService } from '../../../services/user.service';
import { RestaurantService } from '../../../services/restaurant.service';
import { Order } from '../../../model/order.model'
import { Restaurant } from '../../../model/restaurant.model';
import { User } from '../../../model/user.model';
import { ToastrService } from 'ngx-toastr'; 
import {ActivatedRoute, Router} from '@angular/router'; 

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrls: ['./all-orders.component.scss']
})
export class AllOrdersComponent implements OnInit {


  activeOrders : Order[] = [] 
  currentOrder : string = ""
  display = 'none'
  selectedAddress : any = ''; 
  longitude : any = 0; 
  latitude : any = 0; 
  lat =0
  long = 0
  show = false
  constructor(
    private orderService: OrderService,
    private restaurantService : RestaurantService,
    private userService : UserService,
    private toastr: ToastrService, 
    private router: Router,
    ) { }

  ngOnInit(): void {
    this.reset()
    setTimeout(() => {this.getRestaurants()}, 300);  

  }

  getRestaurants(){ 
    this.activeOrders.forEach((element : Order, index : any) => {

      this.restaurantService.findById(element.restaurantId).subscribe((data : Restaurant) => {
        this.activeOrders[index].restaurant = data;
        this.userService.findById(element.customerId).subscribe((data : User) => {
          this.activeOrders[index].customer = data;
        })
      })  

      
    });
  
     
    this.show= true
    console.dir(this.activeOrders)   
     
  }
  reset(){
    this.activeOrders = []
    this.getAllActiveOrders(); 
  }

  selectOrder(id : any){
    this.currentOrder = id
  }

  getAllActiveOrders(){
    
    this.orderService.getAllActiveOrders().subscribe((data : Order[]) => {
      this.activeOrders = data;
    }); 
  }


  showMap(address : any, longitude : any, latitude : any){
    this.selectedAddress = address
    this.lat = latitude
    this.long = longitude
    this.display = 'block'
    
  }

  takeOrder(orderId : string){
    this.orderService.taking(orderId, sessionStorage.getItem("userId")).subscribe(); 
    this.toastr.success('Uspešno preuzimanje porudžbine')
    this.router.navigate(["taken-orders"]); 
  }

}
