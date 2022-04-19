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
              console.log('Kategorija: ' + this.kategorija.naziv);
              this.postavljanjeSlike(this.kategorija.naziv);
            });
        });
    });
  }

  postavljanjeSlike(naziv: string) {
    if (naziv.localeCompare('Svedski') == 0) {
      this.slika =
        'https://media.istockphoto.com/photos/flag-of-sweden-waving-background-picture-id983123698?k=20&m=983123698&s=170667a&w=0&h=RefSD1I_9GUuU-stcBp9xf6ofKQ9RJiIV2Kzd8E17Eg=';
    } else if (naziv.localeCompare('Engleski') == 0) {
      this.slika =
        'https://t4.ftcdn.net/jpg/01/71/57/89/360_F_171578974_eNhE6sEpc6jsK6Py7IxhTbIZZQ7878Wb.jpg';
    } else if (naziv.localeCompare('Francuski') == 0) {
      this.slika =
        'https://thumbs.dreamstime.com/b/french-flag-waving-wind-symbol-france-silk-117113698.jpg';
    } else if (naziv.localeCompare('Nemacki') == 0) {
      this.slika =
        'https://media.istockphoto.com/photos/germany-flag-picture-id475988677?k=20&m=475988677&s=612x612&w=0&h=3AUulSzs5pWt9jkDksiBSdZCLDcc26V70gDWo32CKz0=';
    } else if (naziv.localeCompare('Spanski') == 0) {
      this.slika =
        'https://thumbs.dreamstime.com/b/silk-spain-flag-over-texture-167324992.jpg';
    } else if (naziv.localeCompare('Ruski') == 0) {
      this.slika =
        'https://www.advantour.com/russia/images/symbolics/russia-flag.jpg';
    } else if (naziv.localeCompare('Italijanski') == 0) {
      this.slika =
        'https://www.worldatlas.com/r/w1200/upload/20/07/2a/shutterstock-152854481.jpg';
    }
  }
}
