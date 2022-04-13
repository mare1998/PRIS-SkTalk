import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class KursService {

private BACKEND_BASE="http://localhost:8080/"

  constructor(private httpClient:HttpClient) { }

  dodajNoviKurs(kursForm:any) {
    let params = new HttpParams()
    .set('naziv', kursForm.value.naziv)
    .set('opis', kursForm.value.opis)
    .set('ocekivaniIshod', kursForm.value.ocekivaniIshod)
    .set('maxBrojPolaznika', kursForm.value.maxBrojPolaznika)
    .set('idKategorija', kursForm.value.selectedKategorija)
    .set('idPredavac', kursForm.value.selectedPredavac)

    return this.httpClient.post(this.BACKEND_BASE+"dodaj-kurs",params, {
      headers : new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'})
    })
  }
}
