import { Component, OnInit } from '@angular/core';
import { InvoiceService } from './services/invoice.service';
import { Invoice } from './models/invoice';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html',
  styleUrls: ['./invoice-detail.component.css']
})
export class InvoiceDetailComponent implements OnInit {

  invoice: Invoice;
  title: string = 'Factura';

  constructor(
    private invoiceService: InvoiceService,
    private activatedRouter: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRouter.paramMap.subscribe(params => {
      let id = +params.get('id');
      this.invoiceService.getInvoice(id).subscribe(invoice => 
        this.invoice = invoice);
    });
  }

}
