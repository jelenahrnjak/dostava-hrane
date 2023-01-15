import {Injectable} from '@angular/core'; 
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private _api_url = environment.baseUrl
  private _auth_url = this._api_url + '/auth';
  private _user_url = this._api_url + '/users';  
  private _order_url = this._api_url + '/order-service';  
 

  get auth_url(): string {
    return this._auth_url;
  } 
 
  get user_url(): string {
    return this._user_url;
  }  

  get order_url(): string {
    return this._order_url;
  } 
 
}
