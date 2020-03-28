import { Component, OnInit } from '@angular/core';
import { Client } from './client';
import { ClientService } from './client.service';
import Swal from 'sweetalert2';
import { tap } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { ModalService } from './profile/modal.service';
import { AuthService } from '../users/auth.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {

  swalWithBootstrap: any;
  clients: Client[];
  paginador: any;
  clientSelected: Client;

  constructor( 
    private clientService: ClientService,
    private activatedRoute: ActivatedRoute,
    public modalService: ModalService,
    public authService: AuthService
    ) {
    this.swalWithBootstrap = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger mr-1'
      },
      buttonsStyling: false
    })
   }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe( params => {
      let page: number =  +params.get('page');

      if(!page) {
        page = 0;
      }

      this.clientService.getClients(page).pipe(
        tap(response => {
          this.clients = response.content as Client[],
          this.paginador = response;
        })
      ).subscribe();
      });

    this.modalService.notifyUpload.subscribe(client => {
      this.clients = this.clients.map(originalClient => {
        if (client.id == originalClient.id) {
          originalClient.photo = client.photo;
        }
        return originalClient;
      });
    });
  }

  delete(client: Client): void {
    this.swalWithBootstrap.fire({
      title: '¿Está seguro?',
      text: `¿Seguro que desea eliminar al cliente ${client.firstName} ${client.lastName}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, quiero borrarlo!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then( result => {
      if (result.value) {
        this.clientService.delete(client.id).subscribe(
          () => {
            this.clients = this.clients.filter(cli => cli !== client) //saco de la lista al que elimine
            this.swalWithBootstrap.fire('Cliente Eliminado!',`Cliente ${client.firstName} ${client.lastName} fue eliminado correctamente!`,'success')
          }
        )
      }
    });
  }

  openModal(client: Client): void {
    this.clientSelected = client;
    this.modalService.openModal();
  } 
}
