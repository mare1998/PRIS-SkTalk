import { Component, Input } from '@angular/core';
import { Kategorija } from './model/kategorija';
import { KategorijaService } from './services/kategorija.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'SkTalk';

  @Input() idKategorija: number
  public kategorije: Kategorija[] | undefined;

  constructor(private kategorijaService:KategorijaService){}

  ngOnInit():void{
    console.log("Inicijalizacija app componenta.")
    this.kategorijaService.getKategorije().subscribe((resp:any)=> {
      this.kategorije = resp;
      console.log('Dobavljene kategorije')
    })
  }


  proveriUlogovanost():boolean {
    const tmp =localStorage.getItem('idUloga')
    if (tmp != undefined) {
        return true;
    }
    return false;
  }

  proveriUlogovanostAdmina():boolean {
    const tmp =localStorage.getItem('idUloga')
    if (tmp != undefined && tmp == '1') {
        return true;
    }
    return false;
  }

  proveriUlogovanostPredavaca():boolean {
    const tmp =localStorage.getItem('idUloga')
    if (tmp != undefined && tmp == '2') {
        return true;
    }
    return false;
  }

  proveriUlogovanostKorisnika():boolean {
    const tmp =localStorage.getItem('idUloga')
    if (tmp != undefined && tmp == '3') {
        return true;
    }
    return false;
  }

  logOut(){
    localStorage.clear();
  }
  
}
