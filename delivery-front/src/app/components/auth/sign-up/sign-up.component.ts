import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService, } from '../../../services/core/auth.service';  
import { ToastrService } from 'ngx-toastr';  
import { MapsAPILoader, MouseEvent } from '@agm/core';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {


  form!: FormGroup; 
  passRegex =  new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')
  zoom : number = 6;
  address : string = '' 
  lat = 46.09915226260574;
  lng = 19.659298920686695;

  constructor( 
    private toastr: ToastrService, 
    private authService: AuthService,
    private router: Router, 
    private formBuilder: FormBuilder,) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.maxLength(32)])],
      repeatedPassword: ['', Validators.compose([Validators.required,Validators.maxLength(32)])],
      firstName: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      lastName: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      email: ['', Validators.compose([Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
      phoneNumber: ['', Validators.compose([Validators.required])],
      address: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      longitude: [''],//Validators.compose([Validators.required])],
      latitude: [''],// Validators.compose([Validators.required])],
    }); 
    
  } 
 

  onSubmit(){  

    const pass = this.form.get('password')?.value

    if(!pass.match(this.passRegex)){
      this.toastr.error('Lozinka mora da sadži najmanje 8 karaktera, malo, veliko slovo, cifru i specijalni karakter.')
      return

    }
    
    if(pass != this.form.get('repeatedPassword')?.value){
      this.toastr.error('Lozinke se ne poklapaju')
      return
    }
 

    this.create()
  }

  create(){
    
    this.authService.registration(this.form.value)
      .subscribe(data => { 
        this.toastr.success('Uspešna registracija!')  
        this.router.navigate(['user']);
      },
        error => { 
          console.log('Registration error');  
          this.toastr.error(error['error'].message)
        });
  }

  
  markerDragEnd($event: MouseEvent) {
    console.log($event.coords.lat)
    console.log($event.coords.lng)

    
    this.form.get('postalCode')?.setValue("24300")
    this.form.get('latitude')?.setValue($event.coords.lat)
    this.form.get('longitude')?.setValue($event.coords.lng)  
    
    this.getAddress($event.coords.lat, $event.coords.lng);
  }


  getAddress(latitude : any , longitude: any) {
    
    var geocoder = new google.maps.Geocoder();

    geocoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results : any, status : any) => {
      
      if (status === 'OK') {
        if (results[0]) {
          this.zoom = 6;
          this.address = results[0].formatted_address;
          console.log(results[0])
         

        } else {
          this.toastr.error('Došlo je do greške pri dobavljanju izabrane adrese')  
        }
      } else {
        
        this.toastr.error('Došlo je do greške pri dobavljanju izabrane adrese')  
      }

    });

    console.log(this.address)
  }

  resetAddress(){
    
    this.form.get('address')?.setValue("")
    this.form.get('latitude')?.setValue(this.lat)
    this.form.get('longitude')?.setValue(this.lng)   
    this.address = ""

  } 
}
