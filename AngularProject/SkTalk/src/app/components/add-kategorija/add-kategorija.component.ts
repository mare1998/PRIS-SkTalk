import { Component, OnInit } from '@angular/core';
import { Kategorija } from 'src/app/model/kategorija';

@Component({
  selector: 'app-add-kategorija',
  templateUrl: './add-kategorija.component.html',
  styleUrls: ['./add-kategorija.component.css']
})
export class AddKategorijaComponent implements OnInit {

  private sveKategorije: Kategorija[] | undefined
  constructor() { }

  ngOnInit(): void {
  }

}
