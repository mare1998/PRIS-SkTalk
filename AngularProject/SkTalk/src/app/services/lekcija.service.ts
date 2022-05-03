import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Lekcija } from '../model/lekcija';

@Injectable({
  providedIn: 'root',
})
export class LekcijaService {
  private BACKEND_BASE = 'http://localhost:8080/Talk/';

  constructor(private httpClient: HttpClient) {}

  dodajNovuLekciju(lekcija: Lekcija, idKurs: number) {
    console.log('servis: ' + idKurs);
    const formData = new FormData();
    formData.append('slika', lekcija.slika);
    let params = new HttpParams()
      .set('idKurs', idKurs.toString())
      .set('tekst', lekcija.tekst.toString())
      .set('slika', formData.toString())
      .set('url_videa', lekcija.urlVidea.toString());

    return this.httpClient.post(
      this.BACKEND_BASE + 'predavac/dodajLekciju',
      params
    );
  }
}
