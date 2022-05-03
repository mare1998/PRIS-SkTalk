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
              console.log('Kategorija: ' + this.kategorija.naziv);
              this.postavljanjeSlike(this.kategorija.slika);
            });
        });
    });
  }

  postavljanjeSlike(slika: string) {
    this.slika = slika;
  }

  //klikom na dugme polaznik se prijavljuje na zeljeni kurs
  prijaviSeNaKurs() {
    if (this.proveriUlogovanostKorisnika()) {
      console.log('Prijavi se na kurs');
      this.kursService
        .prijaviSeNaKurs(
          localStorage.getItem('idKorisnika'),
          this.izabraniKurs.idKurs.toString()
        )
        .subscribe((resp: any) => {
          console.log('RESP: ' + resp);
          if (resp == true) {
            alert('Uspesno ste se prijavili na kurs!');
            window.location.reload();
          } else {
            alert('Neuspesno prijavljivanje na kurs, probajte ponovo!');
          }
        });
    } else {
      window.location.href = 'http://localhost:4200/login';
    }
  }

  proveriUlogovanostKorisnika(): boolean {
    console.log('U metodi proveravamo ulogovanost');
    const tmp = localStorage.getItem('uloga');
    if (tmp != undefined && tmp.localeCompare('polaznik') == 0) {
      return true;
    }
    return false;
  }
}
