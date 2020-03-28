import { ItemInvoice } from './item-invoice';
import { Client } from 'src/app/clients/client';

export class Invoice {
    id: number;
    description: string;
    observation: string;
    items: Array<ItemInvoice> = [];
    client: Client;
    total: number;
    createAt: string;

    public calculateTotal(): number {
        this.total = 0;
        this.items.forEach((item: ItemInvoice) => {
            this.total += item.calculateAmount();
        });
        return this.total;
    }
}
