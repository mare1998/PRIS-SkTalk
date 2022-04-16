import { Component, OnInit } from '@angular/core';
import { Kategorija } from 'src/app/model/kategorija';
import { Kurs } from 'src/app/model/kurs';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';

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
  constructor() { }

  ngOnInit(): void {
  }

}
