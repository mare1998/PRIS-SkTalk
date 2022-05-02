import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KurseviComponent } from './components/kursevi/kursevi.component';
import { KategorijeComponent } from './components/kategorije/kategorije.component';
import { AddKursComponent } from './components/add-kurs/add-kurs.component';
import { AddPredavacComponent } from './components/add-predavac/add-predavac.component';
import { AddKategorijaComponent } from './components/add-kategorija/add-kategorija.component';
import { FormsModule } from '@angular/forms';
import { KursItemComponent } from './components/kurs-item/kurs-item.component';
import { KursViewComponent } from './components/kurs-view/kurs-view.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AddLekcijaComponent } from './components/add-lekcija/add-lekcija.component';

@NgModule({
  declarations: [
    AppComponent,
    KurseviComponent,
    KategorijeComponent,
    AddKursComponent,
    AddPredavacComponent,
    AddKategorijaComponent,
    KursItemComponent,
    KursViewComponent,
    LoginComponent,
    RegisterComponent,
    AddLekcijaComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule, RouterModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
