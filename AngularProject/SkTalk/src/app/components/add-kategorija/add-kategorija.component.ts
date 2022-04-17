import { Component, OnInit } from '@angular/core';
import { Kategorija } from 'src/app/model/kategorija';
import { KategorijaService } from 'src/app/services/kategorija.service';

@Component({
  selector: 'app-add-kategorija',
  templateUrl: './add-kategorija.component.html',
  styleUrls: ['./add-kategorija.component.css'],
})
export class AddKategorijaComponent implements OnInit {
  public sveKategorije: Kategorija[] | undefined;
  constructor(private kategorijaService: KategorijaService) {}

  ngOnInit(): void {
    this.kategorijaService.getKategorije().subscribe((resp: any) => {
      this.sveKategorije = resp;
      console.log(resp);
    });
  }

  dodajKategorija(kategorijaForm: any) {
    console.log(kategorijaForm.value.nazivKat);
    this.kategorijaService
      .dodajKategoriju(kategorijaForm.value.nazivKat)
      .subscribe((resp: any) => {
        console.log(resp);
        if (resp == true) {
          alert('Uspesno dodavanje kategorije');
          window.location.href = 'http://localhost:4200/dodaj-kategoriju';
        } else {
          alert('Neuspesno dodavanje kursa!');
        }
      });
  }
}
