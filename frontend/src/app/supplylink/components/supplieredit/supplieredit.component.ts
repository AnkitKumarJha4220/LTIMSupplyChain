import { Component, OnInit } from '@angular/core';
import { Supplier } from '../../types/Supplier';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { of } from 'rxjs';
import { SupplyLinkService } from '../../services/supplylink.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-supplieredit',
    templateUrl: './supplieredit.component.html',
    styleUrls: ['./supplieredit.component.scss'],
})
export class SupplierEditComponent implements OnInit {
    successMessage: string | null = null;
    errorMessage: string | null = null;
    supplierForm!: FormGroup;
    supplier: Supplier | null = null;
    supplierId: number;

    constructor(
        private formBuilder: FormBuilder,
        private supplyLinkService: SupplyLinkService,
        private route: ActivatedRoute,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.supplierForm = this.formBuilder.group({
            supplierName: ["", [Validators.required]],
            email: [{ value: '', disabled: true }, [Validators.required, Validators.email]],
            phone: [""],
            address: [""],
            username: ["", [Validators.required, this.noSpecialCharacters]],
            password: ["", [Validators.required, Validators.minLength(8)]],
            role: ["", [Validators.required]]
        });
        this.route.params.subscribe(params => {
            this.supplierId = params.supplierId;
            this.loadSupplierDetails();
        });
    }

    loadSupplierDetails(): void {
        this.supplyLinkService.getSupplierById(this.supplierId).subscribe({
            next: (supplier) => {
                this.supplier = supplier;
                this.supplierForm.patchValue({
                    supplierName: supplier.supplierName,
                    email: supplier.email,
                    phone: supplier.phone,
                    address: supplier.address,
                    username: supplier.username,
                    role: supplier.role
                });
            },
            error: (error) => {
                this.errorMessage = 'Error loading supplier details.';
                console.log(this.errorMessage);
            }
        })
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
            const updatedSupplier: Supplier = {
                supplierId: this.supplierId,
                supplierName: this.supplierForm.value.supplierName,
                email: this.supplier!.email ?? "",
                phone: this.supplierForm.value.phone,
                address: this.supplierForm.value.address,
                username: this.supplierForm.value.username,
                password: this.supplierForm.value.password,
                role: this.supplierForm.value.role
            };
            this.supplyLinkService.editSupplier(updatedSupplier).subscribe({
                next: (response) => {
                    this.supplier = response;
                    this.successMessage = 'Supplier updated successfully';
                    this.errorMessage = null;
                    this.router.navigate(['/supplylink']);
                },
                error: (error) => {
                    if (error.status === 400) {
                        this.errorMessage = error.error;
                    }
                    else {
                        this.errorMessage = 'Please check the entered data.';
                    }
                    console.log(error);
                }
            });
        } else {
            this.errorMessage = 'Please fill out all required fields correctly.';
            this.successMessage = null;
        }
    }

}
