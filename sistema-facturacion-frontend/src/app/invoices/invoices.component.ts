import { Component, OnInit } from '@angular/core';
import { Invoice } from './models/invoice';
import { ClientService } from '../clients/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, flatMap} from 'rxjs/operators';
import { InvoiceService } from './services/invoice.service';
import { Product } from './models/product';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { ItemInvoice } from './models/item-invoice';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html'
})
export class InvoicesComponent implements OnInit {

  public title: string = 'Nueva Factura';
  public invoice: Invoice = new Invoice();

  public autocompleteControl = new FormControl();
  public filteredProducts: Observable<Product[]>;

  constructor(
    private clientService: ClientService,
    private activatedRoute: ActivatedRoute,
    private invoiceService: InvoiceService,
    private router: Router
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let clientId = +params.get('clientId');
      this.clientService.getClient(clientId).subscribe(client =>
        this.invoice.client = client);
    });

    this.filteredProducts = this.autocompleteControl.valueChanges
    .pipe(
      map(value => typeof value === 'string'? value : value.name),
      flatMap(value => value?  this._filter(value) : [])
    );
  }

  private _filter(value: string): Observable<Product[]> {
    const filterValue = value.toLowerCase();
    return this.invoiceService.filterProducts(filterValue);
  }

  public showName(product?: Product): string | undefined {
    return product? product.name : undefined;
  }

  public selectProduct(event: MatAutocompleteSelectedEvent):  void {
    let product = event.option.value as Product;

    if(this.existItem(product.id)) {
      this.incrementQuantity(product.id);
    } else {
      let newItem = new ItemInvoice();
      newItem.product = product;
      this.invoice.items.push(newItem);
    }

    this.autocompleteControl.setValue('');
    event.option.focus();
    event.option.deselect();
  }

  public updateQuantity(id: number, event: any): void {
    let quantity: number = event.target.value as number;

    if (quantity == 0) {
      return this.deleteItemInvoice(id);
    }

    this.invoice.items = this.invoice.items.map((item: ItemInvoice) => {
      if (id === item.product.id) {
        item.quantity = quantity;
      }
      return item;
    });
   } 

  public existItem(id: number): boolean {
    let exist = false;
    this.invoice.items.forEach((item: ItemInvoice) => {
      if (id === item.product.id) {
        exist = true;
      }
    });
    return exist;
   }

  public incrementQuantity(id: number): void {
    this.invoice.items = this.invoice.items.map((item: ItemInvoice) => {
      if (id === item.product.id) {
        ++item.quantity;
      }
      return item;
    });
  }

  public deleteItemInvoice(id: number): void {
    this.invoice.items = this.invoice.items.filter((item: ItemInvoice) => id !== item.product.id);
  }

  public create(invoiceForm): void {
    let quantityItems = this.invoice.items.length;

    if (quantityItems == 0) {
      this.autocompleteControl.setErrors({'invalid':true});
    }

    if (invoiceForm.form.valid && quantityItems > 0) {
      this.invoiceService.create(this.invoice).subscribe(invoice => {
        Swal.fire(this.title, `Invoice ${invoice.description} creada con éxito!`, "success");
        this.router.navigate(['/invoices', invoice.id]);
      });
    }

  }
}
