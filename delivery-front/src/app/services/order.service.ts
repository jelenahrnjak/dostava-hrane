import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  catchError, map } from 'rxjs/operators'; 

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private apiService: ApiService, 
    private config: ConfigService, 
  ) {
  }

  
  getAllActiveOrders() { 

    return this.apiService.get(this.config.order_url + '/active')
    .pipe(map(orders => { 
      return orders;
    }));
  }

  findAllByCustomer(userId : any) { 

    return this.apiService.get(this.config.order_url + '/customer/' + userId)
    .pipe(map(orders => { 
      return orders;
    }));
  }

  findAllByDeliverer() { 

    return this.apiService.get(this.config.order_url + '/deliverer/' +  sessionStorage.getItem('userId'))
    .pipe(map(orders => { 
      return orders;
    }));
  }

  createOrder(order : any) { 
    const body = {
      'customerId' : sessionStorage.getItem('userId'),
      'restaurantId' : order.restaurantId,
      'orderItems' : order.orderItems,
      'totalPrice' : order.totalPrice
    }
    return this.apiService.post(this.config.order_url + '/order', JSON.stringify(body))
      .pipe(map(() => {
        console.log('Creating order success');
      }));
  }

  cancelOrder(id : any) {
    return this.apiService.put(this.config.order_url + '/canceling/' + id)
    .pipe(map(c => {
      console.log('Canceling order success');
    }))
    .pipe(catchError(error => this.edit(error)));
    ;
  }

  taking(orderId : any, delivererId:any) {
    return this.apiService.put(this.config.order_url + '/delivering/' +orderId + '/' + delivererId)
    .pipe(map(c => {
      console.log('Taking order success');
    }))
    .pipe(catchError(error => this.edit(error)));
    ;
  }

  
  delivering(orderId : any) {
    return this.apiService.put(this.config.order_url + '/delivered/' + orderId)
    .pipe(map(c => {
      console.log('Delivering order success');
    }))
    .pipe(catchError(error => this.edit(error)));
    ;
  }

  edit(error: any): any {
   alert(error)
  }
}
