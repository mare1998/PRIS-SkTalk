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
  styleUrls: ['./kategorije.component.css']
})
export class KategorijeComponent implements OnInit {

  
  public kategorije: Kategorija[] | undefined

  public kursevi: Kurs[] | undefined

  public selectedKat: Kategorija = new Kategorija()
  public trazeniKurs: Kurs | undefined
  private reloadKategorijasList: Subject<any> = new Subject();

  constructor(/*private kategorijaService: KategorijaService, private knjigaService: KursService, private router:Router*/) { }

  ngOnInit(): void {
  }

  onCreate(){
    this.reloadKategorijasList.next();
  }

  kurseviZaKategoriju(idKategorija:number){

  }

  nadjiKurs(searchForm:any){

  }

}
