import { Component, OnInit } from '@angular/core';
import { User } from './user';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  title: String = 'Inicie Sesión';
  user: User;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { 
    this.user = new User();
  }

  ngOnInit() {
    if (this.authService.isAuthenticated()) {
      Swal.fire('Login', `Hola ${this.authService.user.username}, ya estás autenticado!`, 'info');
      this.router.navigate(['/clients']);
    }
  }

  login(): void {
    if (this.user.username == null || this.user.password == null ){
      Swal.fire('Error Login', 'Username o Password vacías', 'error');
      return;
    }
    this.authService.login(this.user).subscribe( response => {
      let accessToken = response.access_token;
      /* let payload = this.authService.getPayload(accessToken);
      console.log(payload); */

      this.authService.saveUser(accessToken);
      this.authService.saveToken(accessToken);
      let user = this.authService.user;
      this.router.navigate(['/clients']);
      Swal.fire('Login', `¡Bienvenido ${user.username}!`, 'success');
    }, err => {
      if (err.status == 400) {
        Swal.fire('Error Login', 'Username o Password incorrecta', 'error');
      } 
    });
  }
  
}
