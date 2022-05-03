import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public username: string = '';
  public password: string = '';

  constructor(
    private korisnikService: KorisnikService,
    private router: Router
  ) {}

  ngOnInit(): void {
    localStorage.clear();
  }

  login() {
    console.log(this.username);
    this.korisnikService
      .login(this.username, this.password)
      .subscribe((resp: any) => {
        if (resp.idKorisnika == 0) {
          console.log(resp);
          alert('Logovanje je neuspešno! Probajte ponovo!'),
            this.router.navigate(['/login']);
        } else {
          console.log(resp.token);
          localStorage.setItem('token', resp.token),
            localStorage.setItem('idKorisnika', resp.idKorisnika),
            localStorage.setItem('uloga', resp.uloga),
            this.router.navigate(['/svi-kursevi']);
        }
      });
  }
}
