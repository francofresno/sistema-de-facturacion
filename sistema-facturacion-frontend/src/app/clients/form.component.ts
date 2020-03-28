
import { Component, OnInit } from '@angular/core';
import { Client } from './client';
import { Region } from './region';
import { ClientService } from './client.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  public client: Client = new Client();
  public title: string = "Create Client";
  public regions: Region[];

  public errors: string[];

  constructor(
    private clientService: ClientService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loadClient();
    this.clientService.getRegions().subscribe(regions => this.regions = regions);
  }

  public loadClient(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.clientService.getClient(id).subscribe(client => this.client = client);
      }
    });
  }

  public create(): void {
    this.clientService.create(this.client).subscribe(
      response => {
        this.router.navigate(['/clients']);
        swal.fire('New Client', `${response.mensaje}`, 'success');
      },
      err => {
        this.errors = err.error.errors as string[];
        console.error('Codigo: ' + err.status);
        console.error(this.errors);
      }
    );
  }

  public update(): void {
    this.client.invoices = null;
    this.clientService.update(this.client).subscribe(
      response => {
        this.router.navigate(['/clients']);
        swal.fire('Client updated', `${response.mensaje}`, 'success');
      },
      err => {
        this.errors = err.error.errors as string[];
        console.error('Codigo: ' + err.status);
        console.error(this.errors);
      }
    );
  }

  public compareRegion(o1: Region, o2: Region): boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }

    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false : o1.id === o2.id;
  }

}
