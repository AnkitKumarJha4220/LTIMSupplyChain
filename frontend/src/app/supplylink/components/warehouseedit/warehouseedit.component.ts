import { Component, OnInit } from "@angular/core";
import { Warehouse } from '../../types/Warehouse';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Supplier } from "../../types/Supplier";
import { SupplyLinkService } from "../../services/supplylink.service";
import { HttpErrorResponse } from "@angular/common/http";
import { ActivatedRoute, Router } from "@angular/router";


@Component({
    selector: 'app-warehouseedit',
    templateUrl: './warehouseedit.component.html',
    styleUrls: ['./warehouseedit.component.scss']
})
export class WarehouseEditComponent implements OnInit {
    warehouseForm!: FormGroup;
    warehouse: Warehouse;
    warehouseId: number;
    successMessage: string | null = null;
    errorMessage: string | null = null;
    suppliers: Supplier[] = [];

    constructor(
        private formBuilder: FormBuilder,
        private supplyLinkService: SupplyLinkService,
        private route: ActivatedRoute,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.warehouseForm = this.formBuilder.group({
            supplier: [{ value: null, disabled: true }, [Validators.required]],
            warehouseName: ["", [Validators.required]],
            location: [""],
            capacity: ["", [Validators.required, Validators.min(0)]],
        });
        this.route.params.subscribe(params => {
            this.warehouseId = params.warehouseId;
            this.loadWarehouseDetails();
        });
    }

    loadWarehouseDetails(): void {
        this.supplyLinkService.getWarehouseById(this.warehouseId).subscribe({
            next: (warehouse) => {
                this.warehouse = warehouse;
                this.warehouseForm.patchValue({
                    warehouseName: warehouse.warehouseName,
                    supplier: warehouse.supplier.supplierName,
                    location: warehouse.location,
                    capacity: warehouse.capacity
                });
            },
            error: (error) => {
                this.errorMessage = 'Error loading warehouse details.';
                console.log(this.errorMessage);
            }
        })
    }

    onSubmit(): void {
        if (this.warehouseForm.valid) {
            const updatedWarehouse: Warehouse = {
                warehouseId: this.warehouseId,
                supplier: this.warehouse.supplier,
                location: this.warehouseForm.value.location,
                warehouseName: this.warehouseForm.value.warehouseName,
                capacity: this.warehouseForm.value.capacity
            }
            this.supplyLinkService.editWarehouse(updatedWarehouse).subscribe({
                next: (response) => {
                    this.warehouse = response;
                    this.successMessage = 'Warehouse updated successfully';
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
            })
        } else {
            this.errorMessage = 'Please fill out all required fields correctly.';
            this.successMessage = null;
        }
    }
}