import { Warehouse } from "./Warehouse";

export class Product {
    productId: number;
    warehouse: Warehouse;
    productName: string;
    productDescription: string;
    quantity: number;
    price: number;

    constructor(
        productId: number,
        warehouse: Warehouse,
        productName: string,
        productDescription: string,
        quantity: number,
        price: number
    ) {
        this.productId = productId;
        this.warehouse = warehouse;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
    }

}

