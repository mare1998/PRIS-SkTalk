import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Kategorija } from '../model/kategorija';
import { Kurs } from '../model/kurs';
import { Predavac } from '../model/predavac';

@Injectable({
  providedIn: 'root',
})
export class KategorijaService {
  private BACKEND_BASE = 'http://localhost:8080/Talk/';

  constructor(private httpClient: HttpClient) {}

  getKategorije(): Observable<Kategorija[]> {
    return this.httpClient.get<Kategorija[]>(
      this.BACKEND_BASE + 'administrator/sveKategorije'
    );
  }

  dodajKategoriju(nazivKategorije: string): any {
    let params = new HttpParams().set('naziv', nazivKategorije);
    return this.httpClient.post(
      this.BACKEND_BASE + 'administrator/dodajKategoriju',
      params,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/x-www-form-urlencoded',
        }),
      }
    );
  }
}
