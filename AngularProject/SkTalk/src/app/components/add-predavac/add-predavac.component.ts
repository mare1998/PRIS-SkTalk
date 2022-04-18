import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PredavacService } from 'src/app/services/predavac.service';

@Component({
  selector: 'app-add-predavac',
  templateUrl: './add-predavac.component.html',
  styleUrls: ['./add-predavac.component.css']
})
export class AddPredavacComponent implements OnInit {

  constructor(private predavacService: PredavacService, private router: Router) { }

  ngOnInit(): void {
  }

  dodavanjePredavaca(predavacForm:any) {
    this.predavacService.dodajPredavaca(predavacForm).subscribe((resp:any)=> {
      if(resp == true) {
        alert("Uspesno dodavanje predavaca")
        window.location.href = "http://localhost:4200/dodaj-predavaca"

      }
      else {
        alert("Greska pri dodavanju predavaca")
      }
    })
  }

}
