import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 
 
import { LoginComponent } from './components/auth/login/login.component';
import { SignUpComponent } from './components/auth/sign-up/sign-up.component';
import { ErrorPageComponent } from './components/core/error-page/error-page.component'; 

import { AuthentificationGuard } from './guards/authentification.guard'
import { RoleguardService as RoleGuard } from './guards/roleguard.service';

//canActivate:[AuthentificationGuard] //OVO DODAVATI U PATH ZA SVE PUTANJE KOJIMA MOGU SVI REGISTROVANI DA PRISTUPE

// canActivate: [RoleGuard],  //OVO DODAVATI U PATH KADA SAMO NEKA ROLA IMA PRISTUP
// data: { 
//   expectedRole: 'ADMIN'  
// },

const routes: Routes = [
  {
    path: "",
    redirectTo: "auth/login",
    pathMatch: "full", 
  },
  {
    path: 'auth/login',
    component: LoginComponent, 
  }, 
  {
    path: 'auth/signup',
    component: SignUpComponent, 
  }, 
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
