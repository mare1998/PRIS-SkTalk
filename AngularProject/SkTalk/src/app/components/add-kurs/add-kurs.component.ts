import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Kategorija } from 'src/app/model/kategorija';
import { Korisnik } from 'src/app/model/korisnik';
import { Predavac } from 'src/app/model/predavac';
import { KategorijaService } from 'src/app/services/kategorija.service';
import { KursService } from 'src/app/services/kurs.service';
import { PredavacService } from 'src/app/services/predavac.service';

@Component({
  selector: 'app-add-kurs',
  templateUrl: './add-kurs.component.html',
  styleUrls: ['./add-kurs.component.css'],
})
export class AddKursComponent implements OnInit {
  public kategorije: Kategorija[];
  public predavaci: Korisnik[];

  public idKategorije: string;
  public idPredavaca: string;

  constructor(
    private kursService: KursService,
    private kategorijaService: KategorijaService,
    private predavacService: PredavacService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.kategorijaService.getKategorije().subscribe((resp: any) => {
      this.kategorije = resp;
    });
    this.predavacService.getPredavaci().subscribe((resp: any) => {
      this.predavaci = resp;
    });
  }

  dodajNoviKurs(kursForm: any) {
    console.log(kursForm);
    this.kursService.dodajNoviKurs(kursForm).subscribe((resp: any) => {
      if (resp == true) {
        alert('Uspesno dodavanje');
        window.location.href = 'http://localhost:4200/dodaj-kurs';
      } else {
        alert('Neuspesno dodavanje kursa!');
      }
    });
  }
}
