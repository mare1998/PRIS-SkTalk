import { Component, OnInit } from '@angular/core';
import { Kurs } from 'src/app/model/kurs';
import { Lekcija } from 'src/app/model/lekcija';
import { KursService } from 'src/app/services/kurs.service';
import { LekcijaService } from 'src/app/services/lekcija.service';

@Component({
  selector: 'app-add-lekcija',
  templateUrl: './add-lekcija.component.html',
  styleUrls: ['./add-lekcija.component.css'],
})
export class AddLekcijaComponent implements OnInit {
  public idKurs: string;
  public kursevi: Kurs[];
  public lekcija: Lekcija;

  constructor(
    private kursService: KursService,
    private lekcijaService: LekcijaService
  ) {
    this.lekcija = new Lekcija();
  }

  ngOnInit(): void {
    this.kursService.getKursevi().subscribe((resp: any) => {
      this.kursevi = resp;
    });
  }

  public onImageUpload(event: any) {
    this.lekcija.slika = event.target.files[0];
  }

  dodajNovuLekciju(lekcijaForm: any) {
    console.log(lekcijaForm.value);
    this.lekcija.tekst = lekcijaForm.value.tekst;
    this.lekcija.urlVidea = lekcijaForm.value.url_videa;
    this.lekcijaService
      .dodajNovuLekciju(this.lekcija, lekcijaForm.value.idKurs)
      .subscribe((resp: any) => {
        if (resp == true) {
          alert('Uspesno dodavanje');
          window.location.href = 'http://localhost:4200/dodaj-lekciju';
        } else {
          alert('Neuspesno dodavanje lekcije!');
        }
      });
  }
}
