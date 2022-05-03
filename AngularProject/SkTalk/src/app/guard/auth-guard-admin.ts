import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuardAdmin implements CanActivate {

    public uloga: string

    constructor(private router:Router){}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        console.log(localStorage.getItem("idUloga"))
        var x = localStorage.getItem("idUloga");
        if (x != null){
            this.uloga = x; 
            if (localStorage.getItem("token") && this.uloga.localeCompare("admin") == 0){
                console.log("proverili smo token i uloge, poklapa se sa adminom.")
                return true;
            }
        }   
        console.log("prosli smo proveru za admin"); 
        alert("Niste ulogovani kao admin!")
        this.router.navigate(["/svi-kursevi"])
        return false;
    }
}