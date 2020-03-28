import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
      if (!this.authService.isAuthenticated()) {
        this.router.navigate(['/login']);
      } else if (this.isTokenExpired()) {
        this.authService.logout();
        this.router.navigate(['/login']);
        return false;
      }
      return this.authService.isAuthenticated();
  }
  
  isTokenExpired(): boolean {
    let token = this.authService.token;
    let payload = this.authService.getPayload(token);
    let now = new Date().getTime() / 1000;

    return payload.exp < now;
  }
}
