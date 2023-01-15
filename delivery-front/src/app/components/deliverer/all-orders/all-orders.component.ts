import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../../services/order.service';
import { Order } from '../../../model/order.model'

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
  lat =0
  long = 0
  constructor(
    // private toastr: ToastrService, 
    private orderService: OrderService,
    ) { }

  ngOnInit(): void {
    this.reset()
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


  showMap(address : any){
    this.selectedAddress = address.street + ' ' + address.number + ', ' + address.city + ' ' + address.postalCode + ', ' + address.country
    this.lat = address.latitude
    this.long = address.longitude
    this.display = 'block'
    
  }

  takeOrder(orderId : string){
    alert(orderId)
  }

}
