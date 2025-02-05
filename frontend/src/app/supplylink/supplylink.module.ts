import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { SupplyLinkRoutingModule } from "./supplylink-routing.module";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { ProductComponent } from "./components/product/product.component";
import { SupplierComponent } from "./components/supplier/supplier.component";
import { WarehouseComponent } from "./components/warehouse/warehouse.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { SharedModule } from "../shared/shared.module";
import { SupplierEditComponent } from "./components/supplieredit/supplieredit.component";
import { WarehouseEditComponent } from "./components/warehouseedit/warehouseedit.component";

@NgModule({
  declarations: [
    ProductComponent,
    SupplierComponent,
    WarehouseComponent,
    DashboardComponent,
    SupplierEditComponent,
    WarehouseEditComponent
  ],
  imports: [
    CommonModule,
    SupplyLinkRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    SharedModule
  ],
  exports: [

  ]
})
export class SupplyLinkModule { }
