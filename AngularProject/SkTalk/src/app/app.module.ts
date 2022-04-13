import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KurseviComponent } from './components/kursevi/kursevi.component';
import { KategorijeComponent } from './components/kategorije/kategorije.component';
import { AddKursComponent } from './components/add-kurs/add-kurs.component';
import { AddPredavacComponent } from './components/add-predavac/add-predavac.component';
import { AddKategorijaComponent } from './components/add-kategorija/add-kategorija.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    KurseviComponent,
    KategorijeComponent,
    AddKursComponent,
    AddPredavacComponent,
    AddKategorijaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
