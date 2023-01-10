import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {

  role = sessionStorage.getItem('role')
  constructor(
    private router: Router,) { }

  ngOnInit(): void {
  }

  goToHomePage(){
    switch(this.role){
      case "USER":
        this.router.navigate(["user"]); 
        break;
      case "DELIVERER":
        this.router.navigate(["deliverer"]);
        break;
      default:
          this.router.navigate(["auth/login"])
      } 
  }

}
