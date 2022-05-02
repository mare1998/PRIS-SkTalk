import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuardPolaznik implements CanActivate {

    public uloga: string

    constructor(private router:Router){}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        console.log(localStorage.getItem("uloga"))
        var x = localStorage.getItem("uloga");
        if (x != null){
            this.uloga = x; 
            if (localStorage.getItem("token") && this.uloga.localeCompare("polaznik") == 0){
                console.log("proverili smo token i uloge, poklapa se sa polaznikom.")
                return true;
            }
        }   
        console.log("prosli smo proveru za polaznik"); 
        alert("Niste ulogovani kao polaznik!")
        this.router.navigate(["/svi-kursevi"])
        return false;
    }
}