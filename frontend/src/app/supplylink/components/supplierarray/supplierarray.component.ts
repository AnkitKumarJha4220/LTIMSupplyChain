import { Component } from '@angular/core';
import { Supplier } from '../../types/Supplier'
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-supplierrarray',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './supplierarray.component.html',
  styleUrls: ['./supplierarray.component.css']
})
export class SupplierArrayComponent {
  supplierList: Supplier[] = [
    new Supplier(1, "Jessica Alba", "jessica@gmail.com", "7368289682", "california", "jessica", "July@12221", "USER"),
    new Supplier(1, "John Wane", "johnwane@gmail.com", "9876543210", "texas", "johnwane", "July@101", "USER"),
    new Supplier(1, "Kristan", "kristan@gmail.com", "9364812638", "NYC", "kristan", "Julll@101", "USER")
  ]
}
