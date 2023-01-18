import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../../services/restaurant.service'
import { Restaurant } from '../../model/restaurant.model' 
import { ActivatedRoute } from '@angular/router';
import { OrderItem } from '../../model/orderItem.model';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.scss']
})
export class RestaurantComponent implements OnInit {

  restaurant : any;
  restId : any = '';
  meals : any;

  constructor(private restaurantService : RestaurantService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {

    
    this.restId = this.route.snapshot.paramMap.get('id');
    this.restaurantService.findById(this.restId).subscribe((data : Restaurant) => {
      this.restaurant = data;
      this.meals = data.meals
      this.meals.forEach((meal : OrderItem)=> {
        meal.count = 0;
      });
      console.dir(this.meals)
    }); 


  }

}
