import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject'; 
import { Restaurant } from '../../model/restaurant.model'
// import Swal from 'sweetalert2';
import { RestaurantService } from '../../services/restaurant.service';
import { Pipe, PipeTransform } from '@angular/core'

@Component({
  selector: 'app-restaurant-view',
  templateUrl: './restaurant-view.component.html',
  styleUrls: ['./restaurant-view.component.scss']
})
export class RestaurantViewComponent implements OnInit {
  
     
  constructor(  
    private route: ActivatedRoute,
    private restaurantService: RestaurantService,
    private router: Router, 
    private formBuilder: FormBuilder,) { }
 
    restaurants : Restaurant[] = []
    searchString : string = ""
    selectedSearchType = 0;

    ngOnInit() {

     this.clear(); 
  
 
    }


    clear(){
    
        this.restaurantService.getAll().subscribe((data : Restaurant[]) => {
          this.restaurants = data;
          console.dir(data)
        }); 
    
    }

    search(){ 
      
      switch(this.selectedSearchType) {
          case 1:
            this.restaurantService.searchByName(this.searchString).subscribe((data : Restaurant[]) => {
              this.restaurants = data;
            }); 
            break;
          case 2:
            this.restaurantService.searchByType(this.searchString).subscribe((data : Restaurant[]) => {
              this.restaurants = data;
            }); 
            break;
          case 3:
            this.restaurantService.searchByMealName(this.searchString).subscribe((data : Restaurant[]) => {
              this.restaurants = data;
            }); 
            break;
          case 4:
            this.restaurantService.searchByMealType(this.searchString).subscribe((data : Restaurant[]) => {
              this.restaurants = data;
            }); 
            break;
          default:
            return
        } 
      

    }
 

    selectRestaurant(id : any){
     
      alert(id)
      
      //this.router.navigate(['meals/' + id]);
    } 




    changeSorting(){

      // if(this.form.get('sorting').value == 1){ 
  
      //   this.items.sort(function(a : any, b:any) { 
      //     return a.price - b.price;})
  
      // }else if(this.form.get('sorting').value == 2){  
  
      //   this.items.sort(function(a : any, b:any) {
  
      //     return b.rating - a.rating  })

      // }else if(this.form.get('sorting').value == 3){

      // return this.items.sort((a : any, b:any) => a.name.localeCompare(b.name));

      // } 
  
    }
}
