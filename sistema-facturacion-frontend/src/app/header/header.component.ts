import { Component } from '@angular/core';
import { AuthService } from '../users/auth.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls:['./header.component.css']
})
export class HeaderComponent {
 title: string = 'Sistema de Facturación'

 constructor(
    public authService: AuthService,
    private router: Router
 ) {}

  logout(): void {
    let username = this.authService.user.username
    this.authService.logout();
    Swal.fire('Logout', `¡${username}, has cerrado sesión con éxito!`, 'success');
    this.router.navigate(['/login']);
  }

}
