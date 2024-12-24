import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map, Observable } from "rxjs";
// import { User } from "../types/user";
import { Supplier } from "../../supplylink/types/Supplier";

export class AuthService {
  

 
  constructor(private http: HttpClient) {}

  login(user: Partial<Supplier>): Observable<{ [key: string]: string }> {
    return new Observable();
  }

  getToken() : string {
    return '';
  }

  getRole() : string {
    return '';
  }

  getUsers(): Observable<Supplier[]> {
    return new Observable();
  }

  createUser(user: Supplier): Observable<Supplier> {
    return new Observable();
  }
}
