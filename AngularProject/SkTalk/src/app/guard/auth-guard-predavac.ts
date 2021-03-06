import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuardPredavac implements CanActivate {

    public uloga: string

    constructor(private router:Router){}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        console.log(localStorage.getItem("uloga"))
        var x = localStorage.getItem("uloga");
        if (x != null){
            this.uloga = x; 
            if (localStorage.getItem("token") && this.uloga.localeCompare("predavac") == 0){
                console.log("proverili smo token i ulogu, poklapa se sa predavacem")
                return true;
            }
        }   
        console.log("prosli smo proveru za predavac"); 
        alert("Niste ulogovani kao predavac!")
        this.router.navigate(["/svi-kursevi"])
        return false;
    }
}