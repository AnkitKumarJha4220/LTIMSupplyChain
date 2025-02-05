
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Product } from '../../types/Product';
import { Warehouse } from '../../types/Warehouse';
import { Observable, of } from 'rxjs';
import { SupplyLinkService } from '../../services/supplylink.service';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
    productForm!: FormGroup;
    warehouses: Observable<Warehouse[]>;
    productError: Observable<string> = of('');
    productSuccess: Observable<string> = of('');
    isFormSubmitted: boolean = false;
    userId: number;

    constructor(
        private formBuilder: FormBuilder,
        private supplyLinkService: SupplyLinkService
    ) { }

    ngOnInit() {
        this.userId = Number(localStorage.getItem("user_id"));
        this.productForm = this.formBuilder.group({
            warehouse: ["", Validators.required],
            productDescription: ["", Validators.required],
            productName: [null, Validators.required],
            quantity: [null, [Validators.required, Validators.min(0)]],
            price: [null, [Validators.required, Validators.min(1)]],
        });
        this.warehouses = this.supplyLinkService.getWarehousesBySupplier(this.userId);
    }

    onSubmit() {
        this.isFormSubmitted = true;
        this.productSuccess = of('');
        this.productError = of('');

        if (this.productForm.invalid) {
            return;
        }
        this.supplyLinkService.addProduct(this.productForm.value).subscribe({
            next: (response) => {
                this.productSuccess = of("Product created successfully");
                this.productForm.reset();
                this.isFormSubmitted = false;
            },
            error: (error) => this.productError = of("Unable to create product")
        });
    }
}
