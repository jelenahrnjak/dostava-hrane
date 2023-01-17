import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw';  
import {  map } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  constructor(
    private apiService: ApiService, 
    private config: ConfigService, 
  ) {
  }

  createUser(user : any) {
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + sessionStorage.getItem('jwt')
    });

    return this.apiService.post(this.config.user_url, JSON.stringify(user), headers)
      .pipe(map(() => {
        console.log('Creating user success');
      }));
  }

  findById(id : any) { 

    return this.apiService.get(this.config.user_url + `/${id}` )
    .pipe(map(user => { 
      return user;
    }));
  }
}
