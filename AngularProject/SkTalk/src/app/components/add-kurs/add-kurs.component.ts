import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Kategorija } from 'src/app/model/kategorija';
import { Predavac } from 'src/app/model/predavac';
import { KursService } from 'src/app/services/kurs.service';

@Component({
  selector: 'app-add-kurs',
  templateUrl: './add-kurs.component.html',
  styleUrls: ['./add-kurs.component.css']
})
export class AddKursComponent implements OnInit {

  public kategorije: Kategorija[]
  public predavaci: Predavac[]

  public selectedKategorija: string
  public selectedPredavac: string

  constructor(private kursService: KursService, private router: Router) { }
  
  ngOnInit(): void {
  }

  dodajNoviKurs(kursForm: any) {
    this.kursService.dodajNoviKurs(kursForm).subscribe((resp: any) => {
      if (resp == true) {
        alert("Uspesno dodavanje")
        window.location.href = "http://localhost:4200/dodaj-kurs"
      }
      else {
        alert("Neuspesno dodavanje kursa!")
      }
    })
  }

}
