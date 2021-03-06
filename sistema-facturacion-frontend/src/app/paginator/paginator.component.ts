import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'paginator-nav',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent implements OnInit, OnChanges {

  @Input() paginador: any;

  pages: number[];

  from: number;
  to: number;

  constructor() { }

  ngOnInit() { 
    this.initPaginator();
  }

  ngOnChanges(changes: SimpleChanges) {
    let paginadorUpdated = changes['paginador'];
    if (paginadorUpdated.previousValue) {
      this.initPaginator();
    }
  }

  private initPaginator(): void {
    if (this.paginador.totalPages > 5) {
      this.from = Math.min( Math.max(1, this.paginador.number-4), this.paginador.totalPages-5 );
      this.to = Math.max( Math.min(this.paginador.totalPages, this.paginador.number+4), 6 );
      this.pages = new Array(this.to - this.from + 1).fill(0).map((_value, index) => index + this.from);
    } else {
      this.pages = new Array(this.paginador.totalPages).fill(0).map((_value, index) => index + 1);
    }
  }

}
