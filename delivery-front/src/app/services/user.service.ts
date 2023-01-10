import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';
import { ApiService } from './core/api.service';
import { ConfigService } from './core/config.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  constructor(
    private apiService: ApiService, 
    private config: ConfigService, 
  ) {
  }

 

}
