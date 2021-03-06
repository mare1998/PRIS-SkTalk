import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Korisnik } from '../model/korisnik';

@Injectable({
  providedIn: 'root',
})
export class PredavacService {
  private BACKEND_BASE = 'http://localhost:8080/Talk/';

  constructor(private httpClient: HttpClient) {}

  getPredavaci(): Observable<Korisnik[]> {
    return this.httpClient.get<Korisnik[]>(
      this.BACKEND_BASE + 'administrator/sviPredavaci'
    );
  }

  dodajPredavaca(predavacForm: any) {
    let params = new HttpParams()
      .set('ime', predavacForm.value.ime)
      .set('prezime', predavacForm.value.prezime)
      .set('staz', predavacForm.value.staz)
      .set('plata', predavacForm.value.plata)
      .set('username', predavacForm.value.username)
      .set('password', predavacForm.value.password);

    return this.httpClient.post(
      this.BACKEND_BASE + 'administrator/dodajPredavaca',
      params,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/x-www-form-urlencoded',
        }),
      }
    );
  }
}
