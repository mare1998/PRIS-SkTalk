import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Kategorija } from 'src/app/model/kategorija';
import { Korisnik } from 'src/app/model/korisnik';
import { Kurs } from 'src/app/model/kurs';
import { KursService } from 'src/app/services/kurs.service';

@Component({
  selector: 'app-kurs-view',
  templateUrl: './kurs-view.component.html',
  styleUrls: ['./kurs-view.component.css']
})
export class KursViewComponent implements OnInit {

  @Input() public izabraniKurs: Kurs
  public predavac: Korisnik
  public kategorija: Kategorija
  
  constructor(private kursService:KursService, private router:Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const nazivKursa = this.route.snapshot.paramMap.get('nazivKursa');
    this.kursService.nadjiKurs(nazivKursa).subscribe((resp:any) => {
      this.izabraniKurs = resp;
    })
    this.kursService.getPredavac(this.izabraniKurs.idKurs).subscribe((resp:any)=> {
      this.predavac = resp;
    })
    this.kursService.getKategorijaKursa(this.izabraniKurs.idKurs).subscribe((resp:any)=> {
      this.kategorija = resp;
    })
  }

}
