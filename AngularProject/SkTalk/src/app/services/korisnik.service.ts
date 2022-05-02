import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class KorisnikService {
  private BACKEND_BASE = 'http://localhost:8080/Talk/security/';

  constructor(private httpClient: HttpClient) {}

  login(korisnickoIme: string, sifra: string) {
    let params = new HttpParams()
      .set('username', korisnickoIme)
      .set('password', sifra);

    console.log(korisnickoIme + ' | ' + sifra);
    return this.httpClient.post(this.BACKEND_BASE + 'login', params, {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
      }),
    });
  }

  register(userForm: any) {
    let params = new HttpParams()
      .set('ime', userForm.value.ime)
      .set('prezime', userForm.value.prezime)
      .set('username', userForm.value.username)
      .set('password', userForm.value.password)
      .set('adresa', userForm.value.adresa)
      .set('telefon', userForm.value.telefon);

    console.log(params);
    return this.httpClient.post(this.BACKEND_BASE + 'registracija', params, {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
      }),
    });
  }
}
