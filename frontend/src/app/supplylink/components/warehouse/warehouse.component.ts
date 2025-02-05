import { Component, OnInit } from "@angular/core";
import { Warehouse } from '../../types/Warehouse';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Supplier } from "../../types/Supplier";
import { SupplyLinkService } from "../../services/supplylink.service";
import { HttpErrorResponse } from "@angular/common/http";


@Component({
  selector: 'app-warehouse',
  templateUrl: './warehouse.component.html',
  styleUrls: ['./warehouse.component.scss']
})
export class WarehouseComponent implements OnInit {
  warehouseForm!: FormGroup;
  warehouse: Warehouse | null = null;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  suppliers: Supplier[] = [];

  constructor(
    private fb: FormBuilder,
    private supplyLinkService: SupplyLinkService
  ) { }

  ngOnInit(): void {
    this.loadSuppliers();
    this.warehouseForm = this.fb.group({
      supplier: [null, [Validators.required]],
      warehouseName: ["", [Validators.required]],
      location: [""],
      capacity: ["", [Validators.required, Validators.min(0)]],
    });
  }

  loadSuppliers(): void {
    this.supplyLinkService.getAllSuppliers().subscribe({
      next: (response) => {
        this.suppliers = response;
      },
      error: (error) => console.log('Error in loading suppliers')
    });
  }

  onSubmit(): void {
    if (this.warehouseForm.valid) {
      this.supplyLinkService.addWarehouse(this.warehouseForm.value).subscribe({
        next: (response) => {
          this.warehouse = response;
          this.successMessage = 'Warehouse created successfully';
          this.errorMessage = null;
          this.warehouseForm.reset();
        },
        error: (error) => this.handleError(error)
      })
    } else {
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.successMessage = null;
    }
  }

  private handleError(error: HttpErrorResponse): void {
    if (error.error instanceof ErrorEvent) {
      this.errorMessage = `Client-side error: ${error.error.message}`;
    } else {
      this.errorMessage = `Server-side error: ${error.status} ${error.message}`;
      if (error.status === 400) {
        this.errorMessage = 'Bad request. Please check your input.';
      }
    }
    this.successMessage = null;
    console.error('An error occurred:', this.errorMessage);
  }
}