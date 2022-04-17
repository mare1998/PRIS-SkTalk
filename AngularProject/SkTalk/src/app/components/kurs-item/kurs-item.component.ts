import { Component, Input, OnInit } from '@angular/core';
import { Kategorija } from 'src/app/model/kategorija';
import { Kurs } from 'src/app/model/kurs';
import {Korisnik} from 'src/app/model/korisnik';
import { KursService } from 'src/app/services/kurs.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-kurs-item',
  templateUrl: './kurs-item.component.html',
  styleUrls: ['./kurs-item.component.css']
})
export class KursItemComponent implements OnInit {

  @Input()course: Kurs = new Kurs()
  kategorija: Kategorija = new Kategorija()
  lecturer: Korisnik = new Korisnik()
  @Input() izabraniKurs: Kurs

  

  constructor(/*private kursService: KursService*/) { }

  ngOnInit(): void {
    // this.kursService.getKategorijaKursa(this.course.idKurs).subscribe((resp:any) =>{
    //   this.kategorija = resp;
    // })
    // this.kursService.getPredavac(this.course.idKurs).subscribe((resp:any) => {
    //   this.lecturer = resp
    // })
  }

  prikazKursa(naziv:String){
    // if (!localStorage.getItem('idKorisnika')){
    //   alert('Niste prijavljeni, ne možete pristupiti ovome!')
    // }
    // else {
    //   this.kursService.nadjiKurs(naziv).subscribe((resp:any) => {
    //     this.izabraniKurs = resp;
    //   })
    //   this.kursService.getPredavac(this.izabraniKurs.idKurs).subscribe((resp: any) =>
    //   {
    //     this.lecturer = resp;
    //   })
    //   window.location.href = "http://localhost:4200/svi-kursevi" //reload stranice
    // }
  }

}
