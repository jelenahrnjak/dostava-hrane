import {Injectable} from '@angular/core'; 
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private _api_url = environment.baseUrl 
  private _user_url = this._api_url + '/users-service';  
  private _order_url = this._api_url + '/order-service';  
  private _restaurant_url = this._api_url + '/restaurant-service';  
  
  get user_url(): string {
    return this._user_url;
  }  

  get order_url(): string {
    return this._order_url;
  } 

  get restaurant_url(): string {
    return this._restaurant_url;
  } 
 
}
