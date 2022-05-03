import {
  HttpClient,
  HttpEvent,
  HttpHeaders,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lekcija } from '../model/lekcija';

@Injectable({
  providedIn: 'root',
})
export class LekcijaService {
  private BACKEND_BASE = 'http://localhost:8080/Talk/';

  constructor(private httpClient: HttpClient) {}

  dodajNovuLekciju(podaci: FormData): Observable<HttpEvent<any>> {
    return this.httpClient.post(
      this.BACKEND_BASE + 'predavac/dodajLekciju',
      podaci,
      {
        reportProgress: true,
        observe: 'events',
      }
    );
  }
}
