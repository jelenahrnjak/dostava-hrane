import {Injectable} from '@angular/core'; 
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private _api_url_auth = environment.baseUrlAuth
  private _api_url_user = environment.baseUrlUser
  private _api_url_order = environment.baseUrlOrder
  private _api_url_recomendation = environment.baseUrlRecommendation

  private _auth_url = this._api_url_auth + '/api/auth';
  private _user_url = this._api_url_auth + '/api/users';  
  private _order_url = this._api_url_auth + '/api/orders';
  private _recommendation_url = this._api_url_auth + '/api/recommendations';  

  get auth_url(): string {
    return this._auth_url;
  } 
 
  get user_url(): string {
    return this._user_url;
  }  
 
  get order_url(): string {
    return this._order_url;
  } 
 
  get recommendation_url(): string {
    return this._recommendation_url;
  }  
}
