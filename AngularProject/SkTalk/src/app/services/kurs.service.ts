import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Kurs } from '../model/kurs';
import { map } from 'rxjs/operators';
import { Korisnik } from '../model/korisnik';
import { Kategorija } from '../model/kategorija';

@Injectable({
  providedIn: 'root',
})
export class KursService {
  private BACKEND_BASE = 'http://localhost:8080/Talk/';

  constructor(private httpClient: HttpClient) {}

  getKursevi(): Observable<Kurs[]> {
    return this.httpClient
      .get<Kurs[]>(this.BACKEND_BASE + 'polaznik/sviKursevi')
      .pipe(
        map((results) =>
          results.sort((kurs1, kurs2) => kurs1.naziv.localeCompare(kurs2.naziv))
        )
      );
  }

  nadjiKurs(nazivKursa: String): Observable<Kurs> {
    return this.httpClient.get<Kurs>(
      this.BACKEND_BASE + 'polaznik/pronadjiKursPoNazivu/' + nazivKursa
    );
  }

  getPredavac(idKurs: number): Observable<Korisnik> {
    return this.httpClient.get<Korisnik>(
      this.BACKEND_BASE + 'polaznik/pronadjiPredavaca/' + idKurs
    );
  }

  getKategorijaKursa(idKurs: number): Observable<Kategorija> {
    return this.httpClient.get<Kategorija>(
      this.BACKEND_BASE + 'polaznik/pronadjiKategoriju/' + idKurs
    );
  }

  getKurseviZaKategoriju(idKategorija: number): Observable<Kurs[]> {
    return this.httpClient.get<Kurs[]>(this.BACKEND_BASE+'polaznik/kurseviZaKategoriju/'+idKategorija);
  }

  dodajNoviKurs(kursForm: any) {
    let params = new HttpParams()
      .set('naziv', kursForm.value.naziv)
      .set('opis', kursForm.value.opis)
      .set('ocekivaniIshod', kursForm.value.ocekivaniIshod)
      .set('idKategorije', kursForm.value.idKategorije)
      .set('idPredavaca', kursForm.value.idPredavaca)

    return this.httpClient.post(
      this.BACKEND_BASE + 'administrator/dodajKurs',
      params,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/x-www-form-urlencoded',
        }),
      }
    );
  }
}
