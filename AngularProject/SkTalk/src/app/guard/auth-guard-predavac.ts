import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuardPredavac implements CanActivate {

    public idUloga: number

    constructor(private router:Router){}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        console.log(localStorage.getItem("idUloga"))
        var x = localStorage.getItem("idUloga");
        if (x != null){
            this.idUloga = +x; //konvertuje string u broj, posto se iz localStoragea sve cita kao string, a nama treba idUloge
            if (localStorage.getItem("token") && this.idUloga == 2){
                console.log("proverili smo token i idUloge, poklapa se sa predavacem")
                return true;
            }
        }   
        console.log("prosli smo proveru za predavac, nije ulogovan predavac vec korisnik"); 
        alert("Niste ulogovani kao predavac!")
        this.router.navigate(["/sve-kategorije"])
        return false;
    }
}