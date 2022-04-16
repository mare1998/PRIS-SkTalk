import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PredavacService {

  private BACKEND_BASE="http://localhost:8080/"

  constructor(private httpClient:HttpClient) { }

  dodajPredavaca(predavacForm: any) {
    let params = new HttpParams()
    .set("ime", predavacForm.value.ime)
    .set("prezime", predavacForm.value.prezime)
    .set("radniStaz", predavacForm.value.radniStaz)
    .set("plata", predavacForm.value.plata)
    .set("username", predavacForm.value.username)
    .set("password", predavacForm.value.password)
  }
}
