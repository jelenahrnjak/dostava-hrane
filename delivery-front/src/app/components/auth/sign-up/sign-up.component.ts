import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService, } from '../../../services/core/auth.service';  
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {


  form!: FormGroup; 
  passRegex =  new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')
  
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
}
