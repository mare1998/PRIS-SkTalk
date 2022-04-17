import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Kategorija } from '../model/kategorija';
import { Kurs } from '../model/kurs';
import { Predavac } from '../model/predavac';

@Injectable({
  providedIn: 'root'
})
export class KategorijaService {

  private BACKEND_BASE="http://localhost:8080/"

  constructor(private httpClient: HttpClient) { }

  getKategorije(): Observable<Kategorija[]> {
    return this.httpClient.get<Kategorija[]>(this.BACKEND_BASE+"sve-kategorije");
  }

  dodajKategoriju(nazivKategorije: string){
    return this.httpClient.post(this.BACKEND_BASE+"administrator/dodajKategoriju", {
      naziv:nazivKategorije
    })
  }
}
