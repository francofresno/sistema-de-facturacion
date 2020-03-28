import { Injectable } from '@angular/core';
import { Client } from './client';
import { Region } from './region';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http'
import { map, catchError } from 'rxjs/operators';

import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private urlEndPoint: string = 'http://localhost:8080/api/clients';

  constructor(
    private http: HttpClient, 
    private router: Router) { }

  getRegions(): Observable<Region[]> {
      return this.http.get<Region[]>(this.urlEndPoint + '/regions');
  }

  getClients(page: number): Observable<any> {
    return this.http.get(this.urlEndPoint + '/page/' + page).pipe(
      map((response: any) => {
        response.content as Client[];
        return response;
      })
    );
  }

  create(client: Client): Observable<any> {
    return this.http.post<any>(this.urlEndPoint, client).pipe(
      catchError( e => {

        if(e.status == 400) {
          return throwError(e);
        }
        return throwError(e);
        // En este caso no vuelvo a otra ruta porque quiero dar la posibilidad de corregir lo que el usuario ingresó mal
      })
    );
  }

  getClient(id): Observable<Client> {
    return this.http.get(`${this.urlEndPoint}/${id}`).pipe(
      map(response => response as Client),
      catchError ( e => {
        if(e.status != 401) {
          this.router.navigate(['/clients']); //Vuelvo a la ruta clients
        }
        return throwError(e);
      })
    );
  }

  update(client: Client): Observable<any> {
    return this.http.put<any>(`${this.urlEndPoint}/${client.id}`, client).pipe(
      catchError( e => {
        return throwError(e);
        // En este caso no vuelvo a otra ruta porque quiero dar la posibilidad de corregir lo que el usuario ingresó mal
      })
    )
  }

  delete(id: number): Observable<Client> {
    return this.http.delete<Client>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  uploadPhoto(file: File, id): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("file", file);
    formData.append("id", id);

    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload-photo`, formData, {
      reportProgress: true });

    return this.http.request(req);
  }

}
