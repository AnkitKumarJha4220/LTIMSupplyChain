export class Supplier {
    supplierId: number | undefined;
    supplierName: string;
    email: string;
    phone: string;
    address: string;
    username: string;
    password: string;
    role?: string;

    constructor(
        supplierId: number | undefined,
        supplierName: string,
        email: string,
        phone: string,
        address: string,
        username: string,
        password: string,
        role?: string
    ) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
