import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Delivery-front'; 
  role = sessionStorage.getItem('role')
  route = this.router.url

  constructor( 
    private router: Router, 
  ) { }

  ngOnInit() { 
  }

  showHeader(){
    
    return this.router.url !== '/auth/login' && this.router.url !== '/auth/signup' && sessionStorage.getItem('role') !== undefined
  }
  
}
