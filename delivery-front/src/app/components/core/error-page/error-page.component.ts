import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.scss']
})
export class ErrorPageComponent implements OnInit {

  constructor(
    private router: Router,) { }

  ngOnInit(): void {
  }

  goBack(){
    const role = sessionStorage.getItem('role')
    switch(role){
      case "CUSTOMER":
        this.router.navigate(["restaurants"]); 
        break;
      case "DELIVERER":
        this.router.navigate(["active-orders"]);
        break;
      default:
          this.router.navigate(["auth/login"])
      } 
  }

}
