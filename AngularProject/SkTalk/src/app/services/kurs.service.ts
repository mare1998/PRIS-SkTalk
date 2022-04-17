import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Kurs } from '../model/kurs';
import { map } from 'rxjs/operators'
import { Predavac } from '../model/predavac';
import { Kategorija } from '../model/kategorija';

@Injectable({
  providedIn: 'root'
})
export class KursService {

private BACKEND_BASE="http://localhost:8080/"

  constructor(private httpClient:HttpClient) { }

  getKursevi():Observable<Kurs[]>{
    return this.httpClient.get<Kurs[]>(this.BACKEND_BASE+"polaznik/sviKursevi").pipe(map(results => results.sort((kurs1, kurs2) => kurs1.naziv.localeCompare(kurs2.naziv))))
  }

  nadjiKurs(nazivKursa: String): Observable<Kurs> {
    return this.httpClient.get<Kurs>(this.BACKEND_BASE+'polaznik/kurs-po-imenu/'+nazivKursa);
  }

  getPredavac(idKurs: number):Observable<Predavac>{
    return this.httpClient.get<Predavac>(this.BACKEND_BASE+'polaznik/predavac-za-kurs/'+idKurs)
  }

  getKategorijaKursa(idKurs: number): Observable<Kategorija>{
    return this.httpClient.get<Kategorija>(this.BACKEND_BASE+'polaznik/kategorija-kursa/'+idKurs)
  }

  dodajNoviKurs(kursForm:any) {
    let params = new HttpParams()
    .set('naziv', kursForm.value.naziv)
    .set('opis', kursForm.value.opis)
    .set('ocekivaniIshod', kursForm.value.ocekivaniIshod)
    .set('maxBrojPolaznika', kursForm.value.maxBrojPolaznika)
    .set('idKategorija', kursForm.value.selectedKategorija)
    .set('idPredavac', kursForm.value.selectedPredavac)

    return this.httpClient.post(this.BACKEND_BASE+"administrator/dodaj-kurs",params, {
      headers : new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'})
    })
  }
}
