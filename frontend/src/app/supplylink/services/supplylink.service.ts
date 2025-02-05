import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Supplier } from "../types/Supplier";
import { Warehouse } from "../types/Warehouse";
import { Product } from "../types/Product";

@Injectable({
  providedIn: "root",
})
export class SupplyLinkService {
  private baseUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  addSupplier(supplier: Supplier): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  editSupplier(supplier: Supplier): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  deleteSupplier(supplierId: number): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  getSupplierById(supplierId: number): Observable<Supplier> {
    // Implementation goes here
    return new Observable<Supplier>();
  }

  getAllSuppliers(): Observable<Supplier[]> {
    // Implementation goes here
    return new Observable<Supplier[]>();
  }

  addWarehouse(warehouse: Warehouse): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  editWarehouse(warehouse: Warehouse): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  deleteWarehouse(warehouseId: number): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  getWarehouseById(warehouseId: number): Observable<Warehouse> {
    // Implementation goes here
    return new Observable<Warehouse>();
  }

  getAllWarehouses(): Observable<Warehouse[]> {
    // Implementation goes here
    return new Observable<Warehouse[]>();
  }

  getWarehousesBySupplier(supplierId: number | null): Observable<Warehouse[]> {
    // Implementation goes here
    return new Observable<Warehouse[]>();
  }

  addProduct(product: Product): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  editProduct(product: Product): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  deleteProduct(productId: number): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  getProductById(productId: number): Observable<Product> {
    // Implementation goes here
    return new Observable<Product>();
  }

  getAllProducts(): Observable<Product[]> {
    // Implementation goes here
    return new Observable<Product[]>();
  }

  getAllProductByWarehouse(warehouseId: string | null): Observable<Product[]> {
    // Implementation goes here
    return new Observable<Product[]>();
  }

}
