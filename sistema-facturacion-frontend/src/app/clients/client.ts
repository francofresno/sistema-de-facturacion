import { Invoice } from '../invoices/models/invoice';
import { Region } from './region';

export class Client {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    createAt: string;
    photo: string;
    region: Region;
    invoices: Array<Invoice> = [];
}
