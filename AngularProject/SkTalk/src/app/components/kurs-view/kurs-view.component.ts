import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Kategorija } from 'src/app/model/kategorija';
import { Korisnik } from 'src/app/model/korisnik';
import { Kurs } from 'src/app/model/kurs';
import { KursService } from 'src/app/services/kurs.service';

@Component({
  selector: 'app-kurs-view',
  templateUrl: './kurs-view.component.html',
  styleUrls: ['./kurs-view.component.css'],
})
export class KursViewComponent implements OnInit {
  @Input() public izabraniKurs: Kurs;
  public predavac: Korisnik;
  public kategorija: Kategorija;
  public slika: string;

  constructor(
    private kursService: KursService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const nazivKursa = this.route.snapshot.paramMap.get('nazivKursa');
    console.log(nazivKursa);
    this.kursService.nadjiKurs(nazivKursa).subscribe((resp: any) => {
      this.izabraniKurs = resp;
      console.log(this.izabraniKurs.idKurs);
      this.kursService
        .getPredavac(this.izabraniKurs.idKurs)
        .subscribe((resp: any) => {
          this.predavac = resp;
          this.kursService
            .getKategorijaKursa(this.izabraniKurs.idKurs)
            .subscribe((resp: any) => {
              this.kategorija = resp;
            });
          console.log(this.predavac.ime);
        });
    });

    this.slika =
      'https://media.istockphoto.com/photos/flag-of-sweden-waving-background-picture-id983123698?k=20&m=983123698&s=170667a&w=0&h=RefSD1I_9GUuU-stcBp9xf6ofKQ9RJiIV2Kzd8E17Eg=';
    console.log('SLIKAAAAA ' + this.slika);
    if (this.kategorija.naziv.localeCompare('Svedski')) {
      this.slika =
        'https://media.istockphoto.com/photos/flag-of-sweden-waving-background-picture-id983123698?k=20&m=983123698&s=170667a&w=0&h=RefSD1I_9GUuU-stcBp9xf6ofKQ9RJiIV2Kzd8E17Eg=';
    }
  }
}
