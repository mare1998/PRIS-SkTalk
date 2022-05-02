import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LekcijaService {
  private BACKEND_BASE = 'http://localhost:8080/Talk/';

  constructor(private httpClient: HttpClient) {}

  dodajNovuLekciju(lekcijaForm: any) {
    let params = new HttpParams()
      .set('tekst', lekcijaForm.value.tekst)
      .set('slika', lekcijaForm.value.slika)
      .set('url_videa', lekcijaForm.value.url_videa)
      .set('idKurs', lekcijaForm.value.idKurs);

    return this.httpClient.post(
      this.BACKEND_BASE + 'predavac/dodajLekciju',
      params,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/x-www-form-urlencoded',
        }),
      }
    );
  }
}
