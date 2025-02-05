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
    return this.http.post<any>(`${this.baseUrl}/supplier`, supplier);
  }

  editSupplier(supplier: Supplier): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/supplier/${supplier.supplierId}`, supplier);
  }

  deleteSupplier(supplierId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/supplier/${supplierId}`);
  }

  getSupplierById(supplierId: number): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.baseUrl}/supplier/${supplierId}`);
  }

  getAllSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/supplier`);
  }

  addWarehouse(warehouse: Warehouse): Observable<any> {
    return this.http.post<Warehouse>(`${this.baseUrl}/warehouse`, warehouse);
  }

  editWarehouse(warehouse: Warehouse): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/warehouse/${warehouse.warehouseId}`, warehouse);
  }

  deleteWarehouse(warehouseId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/warehouse/${warehouseId}`);
  }

  getWarehouseById(warehouseId: number): Observable<Warehouse> {
    return this.http.get<Warehouse>(`${this.baseUrl}/warehouse/${warehouseId}`);
  }

  getAllWarehouses(): Observable<Warehouse[]> {
    return this.http.get<Warehouse[]>(`${this.baseUrl}/warehouse`);
  }

  getWarehousesBySupplier(supplierId: number): Observable<Warehouse[]> {
    return this.http.get<Warehouse[]>(`${this.baseUrl}/warehouse/supplier/${supplierId}`);
  }

  addProduct(product: Product): Observable<any> {
    return this.http.post<Product>(`${this.baseUrl}/product`, product);
  }

  editProduct(product: Product): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/product/${product.productId}`, product);
  }

  deleteProduct(productId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/product/${productId}`);
  }

  getProductById(productId: number): Observable<Product> {
    return this.http.get<Product>(`${this.baseUrl}/product/${productId}`);
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/product`);
  }

  getAllProductByWarehouse(warehouseId: number | null): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/product/warehouse/${warehouseId}`);
  }

}
