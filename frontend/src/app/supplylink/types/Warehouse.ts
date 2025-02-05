import { Supplier } from "./Supplier";

export class Warehouse {
    warehouseId: number;
    supplier: Supplier;
    warehouseName: string;
    location: string;
    capacity: number;

    constructor(
        warehouseId: number,
        supplier: Supplier,
        warehouseName: string,
        location: string,
        capacity: number
    ) {
        this.warehouseId = warehouseId;
        this.supplier = supplier;
        this.warehouseName = warehouseName;
        this.location = location;
        this.capacity = capacity;
    }
}

