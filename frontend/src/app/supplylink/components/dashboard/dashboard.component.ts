
import { Component, OnInit } from '@angular/core';
import { SupplyLinkService } from '../../services/supplylink.service';
import { Supplier } from '../../types/Supplier';
import { Warehouse } from '../../types/Warehouse';
import { Product } from '../../types/Product';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
    suppliers: Supplier[] = [];
    warehouses: Warehouse[] = [];
    products: Product[] = [];

    userSupplier: Supplier;
    supplierWarehouses: Warehouse[] = [];
    selectedWarehouseProducts: Product[] = [];
    selectedWarehouseId: number | undefined;

    role: string | null;
    userId: number;

    // Popup variables
    showProductPopup: boolean = false;
    productForm: FormGroup;
    currentProduct: Product | null = null;

    constructor(private supplyLinkService: SupplyLinkService, private router: Router, private formBuilder: FormBuilder) { }

    ngOnInit(): void {
        this.role = localStorage.getItem("role");
        this.userId = Number(localStorage.getItem("user_id"));
        this.productForm = this.formBuilder.group({
            productName: ['', Validators.required],
            productDescription: ['', Validators.required],
            quantity: [0, Validators.required],
            price: [0, Validators.required]
        });
        if (this.role === 'ADMIN') {
            this.loadAdminData();
        } else {
            this.loadUserData();
        }
    }

    loadAdminData(): void {
        this.supplyLinkService.getAllSuppliers().subscribe({
            next: (response) => {
                this.suppliers = response;
            },
            error: (error) => console.log('Error loading suppliers', error)
        });

        this.supplyLinkService.getAllWarehouses().subscribe({
            next: (response) => {
                this.warehouses = response;
            },
            error: (error) => console.log('Error loading warehouses', error)
        });

        this.supplyLinkService.getAllProducts().subscribe({
            next: (response) => {
                this.products = response;
            },
            error: (error) => console.log('Error loading products', error)
        });
    }

    editSupplier(supplier: Supplier): void {
        this.router.navigate(['/supplylink/supplier/edit', { supplierId: supplier.supplierId, name: supplier.supplierName, email: supplier.email, username: supplier.username, password: supplier.password, role: supplier.role }]);

    }

    deleteSupplier(supplier: any): void {
        let conf = confirm("Do You Really Want To Delete Supplier");
        if (conf) {
            this.supplyLinkService.deleteSupplier(supplier.supplierId).subscribe(
                () => {
                    alert('Supplier deleted successfully.');
                    this.loadAdminData();
                },
                (error) => {
                    console.error('Error deleting supplier:', error);
                }
            );
        }
    }

    editWarehouse(warehouse: any): void {
        console.log(warehouse);
        this.router.navigate(['/supplylink/warehouse/edit', {
            warehouseId: warehouse.warehouseId, balance: warehouse.balance, supplierId: warehouse.supplier.supplierId,
            name: warehouse.supplier.name, username: warehouse.supplier.username, password: warehouse.supplier.pasword, email: warehouse.supplier.email, role: warehouse.supplier.role
        }]);
    }

    deleteWarehouse(warehouse: any): void {
        let conf = confirm("Do You Really Want To Delete Warehouse");
        if (conf) {
            this.supplyLinkService.deleteWarehouse(warehouse.warehouseId).subscribe(
                () => {
                    alert('Warehouse deleted successfully.');
                    this.loadAdminData();
                },
                (error) => {
                    console.error('Error deleting Warehouse:', error);
                }
            );
        }
    }

    loadUserData(): void {
        this.supplyLinkService.getSupplierById(this.userId).subscribe({
            next: (supplier) => {
                this.userSupplier = supplier;
                this.supplyLinkService.getWarehousesBySupplier(supplier.supplierId!).subscribe({
                    next: (warehouses) => {
                        this.supplierWarehouses = warehouses;
                        this.selectedWarehouseId = this.supplierWarehouses[0].warehouseId;
                        this.loadProductsForWarehouse(this.selectedWarehouseId);
                    },
                    error: (error) => console.error('Error loading warehouses for supplier:', error)
                })
            },
            error: (error) => console.error('Error loading supplier data:', error)
        });
    }

    loadProductsForWarehouse(warehouseId: number): void {
        this.supplyLinkService.getAllProductByWarehouse(warehouseId).subscribe({
            next: (products) => {
                this.selectedWarehouseProducts = products;
            },
            error: (error) => console.error(`Error loading products for warehouse ${warehouseId}:`, error)
        })
    }

    onWarehouseSelect(warehouse: Warehouse): void {
        this.selectedWarehouseId = warehouse.warehouseId;
        this.supplyLinkService.getAllProductByWarehouse(warehouse.warehouseId).subscribe({
            next: (products) => {
                this.selectedWarehouseProducts = products;
            },
            error: (error) => console.error(`Error loading products for warehouse ${warehouse.warehouseId}:`, error)
        })
    }

    editProduct(product: Product): void {
        this.showProductPopup = true;
        this.currentProduct = product;

        this.productForm.patchValue({
            productName: product.productName,
            productDescription: product.productDescription,
            quantity: product.quantity,
            price: product.price
        });
    }


    closeProductPopup(): void {
        this.showProductPopup = false;
    }

    onProductFormSubmit(): void {
        if (this.productForm.valid && this.currentProduct) {
            const updatedProduct: Product = {
                productId: this.currentProduct.productId,
                warehouse: this.currentProduct.warehouse,
                productName: this.productForm.value.productName,
                productDescription: this.productForm.value.productDescription,
                quantity: this.productForm.value.quantity,
                price: this.productForm.value.price
            }
            this.supplyLinkService.editProduct(updatedProduct).subscribe({
                next: () => {
                    this.loadProductsForWarehouse(this.selectedWarehouseId!);
                    this.closeProductPopup();
                },
                error: (error) => console.error('Error updating product:', error)
            })
        }
    }

    deleteProduct(product: Product): void {
        if (confirm('Are you sure you want to delete this product?')) {
            this.supplyLinkService.deleteProduct(product.productId).subscribe({
                next: () => {
                    this.selectedWarehouseProducts = this.selectedWarehouseProducts.filter(p => p.productId !== product.productId);
                    if (this.selectedWarehouseId) {
                        this.loadProductsForWarehouse(this.selectedWarehouseId);
                    }
                },
                error: (error) => console.error('Error deleting product:', error)

            })
        }
    }

    addProductToWarehouse(newProduct: Product, warehouseId: number): void {
        this.supplyLinkService.getWarehouseById(warehouseId).subscribe({
            next: (warehouse) => {
                newProduct.warehouse = warehouse;
                this.supplyLinkService.addProduct(newProduct).subscribe({
                    next: (addedProduct) => {
                        this.loadProductsForWarehouse(warehouseId);
                    },
                    error: (error) => console.error('Error adding product:', error)
                })
            },
            error: (error) => console.error('Error loading warehouse by ID:', error)
        });
    }
}
