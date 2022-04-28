import { Component, OnInit } from '@angular/core';
import { Kurs } from 'src/app/model/kurs';
import { KursService } from 'src/app/services/kurs.service';
import { LekcijaService } from 'src/app/services/lekcija.service';

@Component({
  selector: 'app-add-lekcija',
  templateUrl: './add-lekcija.component.html',
  styleUrls: ['./add-lekcija.component.css']
})
export class AddLekcijaComponent implements OnInit {

  public idKurs: string;
  public kursevi: Kurs[];

  constructor(private kursService: KursService, private lekcijaService: LekcijaService) { }

  ngOnInit(): void {
    this.kursService.getKursevi().subscribe((resp: any) => {
      this.kursevi = resp;
    });
  }

  dodajNovuLekciju(lekcijaForm: any) {
    console.log(lekcijaForm);
    this.lekcijaService.dodajNovuLekciju(lekcijaForm).subscribe((resp: any) => {
      if (resp == true) {
        alert('Uspesno dodavanje');
        window.location.href = 'http://localhost:4200/dodaj-lekciju';
      } else {
        alert('Neuspesno dodavanje kursa!');
      }
    });
  }

}
