import { Component, OnInit } from '@angular/core';
import { Kategorija } from 'src/app/model/kategorija';
import { Kurs } from 'src/app/model/kurs';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { KategorijaService } from 'src/app/services/kategorija.service';
import { KursService } from 'src/app/services/kurs.service';

@Component({
  selector: 'app-kategorije',
  templateUrl: './kategorije.component.html',
  styleUrls: ['./kategorije.component.css'],
})
export class KategorijeComponent implements OnInit {
  public kategorije: Kategorija[] | undefined;

  public kursevi: Kurs[] | undefined;
  public filtriraniKursevi: Kurs[]| undefined;

  public selectedKat: Kategorija = new Kategorija();
  public trazeniKurs: Kurs | undefined;
  private reloadKategorijasList: Subject<any> = new Subject();

  constructor(
    private kategorijaService: KategorijaService,
    private kursService: KursService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.kategorijaService.getKategorije().subscribe((resp: any) => {
      this.kategorije = resp;
    });
    this.kursService.getKursevi().subscribe((resp: any) => {
      (this.kursevi = resp), (this.trazeniKurs = undefined);
    });
  }

  onCreate() {
    this.reloadKategorijasList.next();
  }

  kategorijaSelected(value: number){
    console.log(value);
    this.kursService.getKurseviZaKategoriju(value).subscribe((resp:any) => {
      this.filtriraniKursevi = resp;
      console.log('Dobavljeni kursevi');
      this.kursevi = undefined;
      window.location.reload();
    })
  }

  nadjiKurs(searchForm: any) {
    this.kursService.nadjiKurs(searchForm.value.nazivKnjige).subscribe((resp:any) => {
      if (resp == null) {
        alert("Nema kursa sa prosledjenim nazivom!");
        window.location.href = "http://localhost:4200/svi-kursevi"
      } else {
        const naziv = searchForm.value.nazivKursa;
        this.router.navigate(["/prikaz-kursa/"+naziv])
      }
    })
  }
}
