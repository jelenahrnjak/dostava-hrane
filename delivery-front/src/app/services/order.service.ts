import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

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

  findAllByDeliverer(delivererId : any) { 

    return this.apiService.get(this.config.order_url + '/customer/' + delivererId)
    .pipe(map(orders => { 
      return orders;
    }));
  }

  createOrder(order : any) { 
    const body = {
      'customerId' : sessionStorage.getItem('userId'),
      'restaurant' : order.restaurantId,
      'orderItems' : order.orderItems
    }
    return this.apiService.post(this.config.order_url + '/order', JSON.stringify(body))
      .pipe(map(() => {
        console.log('Creating order success');
      }));
  }
}
