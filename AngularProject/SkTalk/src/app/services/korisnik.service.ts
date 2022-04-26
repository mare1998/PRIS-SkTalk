import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class KorisnikService {

  private BACKEND_BASE="http://localhost:8080/Talk/korisnik/"

  constructor(private httpClient: HttpClient) { }

  
  login(username:string, password:string){
    return this.httpClient.post(this.BACKEND_BASE+"login", {
      korisnickoIme:username,
      sifra:password
    })
  }
}
