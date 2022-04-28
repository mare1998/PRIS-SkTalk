import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private korisnikService: KorisnikService, private router: Router) { }

  ngOnInit(): void {
  }

  register(userForm: any) {
    console.log(userForm.value.username)
    this.korisnikService.register(userForm).subscribe((resp: any) => {
      if (resp == true) {
        alert("Uspešno ste se  registrovali na sistem!")
        this.router.navigate(['login'])
      } else {
        alert("Postoji već korisnik sa ovim korisničkim imenom! Probajte ponovo")
      }
    })
  }

}
