import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Supplier } from '../../types/Supplier';
import { of } from 'rxjs';

@Component({
  selector: 'app-suppliersample',
  standalone: true,
  imports: [], // Add necessary imports
  templateUrl: './suppliersample.component.html',
  styleUrls: ['./suppliersample.component.css'] 
})
export class SupplierSampleComponent {
supplier:Supplier=new Supplier(1,"Rajkumar","rajkumar@gmail.com","1234567890","Sirgapur","rajkumar45","Rajkumar@123","Software Engineer");
}