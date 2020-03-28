import { Component, OnInit, Input } from '@angular/core';
import { Client } from '../client';
import { ClientService } from '../client.service';
import Swal from 'sweetalert2'
import { HttpEventType } from '@angular/common/http';
import { ModalService } from './modal.service';
import { InvoiceService } from 'src/app/invoices/services/invoice.service';
import { Invoice } from 'src/app/invoices/models/invoice';
import { AuthService } from 'src/app/users/auth.service';

@Component({
  selector: 'profile-client',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @Input() public client: Client;
  
  public title: string = "Perfil del cliente";
  public progress: number = 0;
  public photoSelected: File;
  private swalWithBootstrap: any;

  constructor(
    private clientService: ClientService,
    public modalService: ModalService,
    public authService: AuthService,
    private invoiceService: InvoiceService
  ) { 
    this.swalWithBootstrap = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger mr-2'
      },
      buttonsStyling: false
    })
  }

  ngOnInit() {}

  selectPhoto(event): void {
    this.photoSelected = event.target.files[0];
    this.progress = 0;
    if (this.photoSelected.type.indexOf('image') < 0) {
      Swal.fire('Error', 'No es una imagen :(', 'error');
      this.photoSelected = null;
    }
  }

  uploadPhoto(): void {
    if (!this.photoSelected) {
      Swal.fire('Error', 'Debes seleccionar una imagen', 'error');
    } else {
      this.clientService.uploadPhoto(this.photoSelected, this.client.id)
      .subscribe( event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round((event.loaded/event.total)*100);
        } else if (event.type === HttpEventType.Response) {
          let response: any = event.body;
          this.client = response.client as Client;

          this.modalService.notifyUpload.emit(this.client);
          Swal.fire('La imagen fue subida satisfactoriamente', response.mensaje,'success')
        }
      });
    }
  }

  closeModal(): void {
    this.modalService.closeModal();
    this.photoSelected = null;
    this.progress = 0;
  }

  delete(invoice: Invoice): void {
    this.swalWithBootstrap.fire({
      title: '¿Está seguro?',
      text: `¿Seguro que desea eliminar la factura ${invoice.description}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, quiero borrarla!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then( result => {
      if (result.value) {
        this.invoiceService.delete(invoice.id).subscribe(
          () => {
            this.client.invoices = this.client.invoices.filter(inv => inv !== invoice)
            this.swalWithBootstrap.fire('Factura Eliminada!',`Factura ${invoice.description} fue eliminada correctamente!`,'success')
          }
        )
      }
    });
  }
}
