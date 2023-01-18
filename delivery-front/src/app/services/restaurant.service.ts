import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw';  
import {  map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
 
  constructor(
    private apiService: ApiService, 
    private config: ConfigService, 
  ) {
  }

  getAll() { 

    return this.apiService.get(this.config.restaurant_url)
    .pipe(map(restaurants => { 
      return restaurants;
    }));
  }
  
  searchByName(search : string) { 

    return this.apiService.get(this.config.restaurant_url + '/search/name/' + search)
    .pipe(map(restaurants => { 
      return restaurants;
    }));
  }

  searchByType(search : string) { 

    return this.apiService.get(this.config.restaurant_url + '/search/type/' + search)
    .pipe(map(restaurants => { 
      return restaurants;
    }));
  }

  searchByMealName(search : string) { 

    return this.apiService.get(this.config.restaurant_url + '/search/mealname/' + search)
    .pipe(map(restaurants => { 
      return restaurants;
    }));
  }

  
  searchByMealType(search : string) { 

    return this.apiService.get(this.config.restaurant_url + '/search/meal/' + search)
    .pipe(map(restaurants => { 
      return restaurants;
    }));
  }


  findById(id : any) { 

    return this.apiService.get(this.config.restaurant_url + `/${id}` )
    .pipe(map(restaurant => { 
      return restaurant;
    }));
  }

}
