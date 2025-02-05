import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Observable, catchError, of, switchMap, tap, throwError } from "rxjs";
import { Router } from "@angular/router";
import { AuthService } from "../../services/auth.service";

@Component({
    selector: "app-login",
    templateUrl: "./login.component.html",
    styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    errorMessage: string | null = null;

    constructor(
        private formBuilder: FormBuilder,
        private authService: AuthService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            username: ["", [Validators.required]],
            password: ["", Validators.required],

        });
    }

    onSubmit(): void {
        if (this.loginForm.valid) {
            const { username, password } = this.loginForm.value;
            this.authService.login({ username, password }).subscribe({
                next: (response) => {
                    console.log(response);
                    localStorage.setItem("token", response.token);
                    localStorage.setItem("role", response.roles);
                    localStorage.setItem("user_id", response.userId);
                    console.log(localStorage.getItem("role"));
                    this.router.navigate(["/supplylink"]);
                },
                error: (error) => {
                    console.log(error);
                    this.errorMessage = "Please check the username and password.";
                }
            });
        } else {
            this.errorMessage = "Please check the username and password.";
        }
    }
}
