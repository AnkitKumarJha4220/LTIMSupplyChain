import { Component, OnInit } from '@angular/core';
import { Supplier } from '../../types/Supplier';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { of } from 'rxjs';
import { SupplyLinkService } from '../../services/supplylink.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss'],
})
export class SupplierComponent implements OnInit {
  successMessage: string | null = null;
  errorMessage: string | null = null;
  supplierForm!: FormGroup;
  supplier: Supplier | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private supplyLinkService: SupplyLinkService
  ) {}

  ngOnInit(): void {
    this.supplierForm = this.formBuilder.group({
      supplierName: ["", [Validators.required]],
      email: ["", [Validators.required, Validators.email]],
      phone: [""],
      address: [""],
      username: ["", [Validators.required, this.noSpecialCharacters]],
      password: ["", [Validators.required, Validators.minLength(8)]],
      role: ["", [Validators.required]]
    });
  }

  private noSpecialCharacters(control: any): { [key: string]: boolean } | null {
    const SPECIAL_CHARACTERS_REGEX = /[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/;
    if (SPECIAL_CHARACTERS_REGEX.test(control.value)) {
      return { specialCharacters: true };
    }
    return null;
  }

  onSubmit(): void {
    if (this.supplierForm.valid) {
      this.supplyLinkService.addSupplier(this.supplierForm.value).subscribe({
        next: (response) => {
          this.supplier = response;
          this.successMessage = 'Supplier created successfully';
          this.errorMessage = null;
          this.supplierForm.reset();
        },
        error: (error) => this.handleError(error)
      });
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
