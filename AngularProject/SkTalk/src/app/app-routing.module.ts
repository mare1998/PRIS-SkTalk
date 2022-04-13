import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddKategorijaComponent } from './components/add-kategorija/add-kategorija.component';
import { AddKursComponent } from './components/add-kurs/add-kurs.component';
import { AddPredavacComponent } from './components/add-predavac/add-predavac.component';
import { KurseviComponent } from './components/kursevi/kursevi.component';

const routes: Routes = [
  { path: '', redirectTo: 'svi-kursevi', pathMatch: 'full' },
  { path: 'svi-kursevi', component: KurseviComponent},  
  { path: 'dodaj-kurs', component: AddKursComponent /*canActivate:[AuthGuardRadnik]*/},
  { path: 'dodaj-predavaca', component: AddPredavacComponent /*canActivate:[AuthGuardRadnik]*/},
  { path: 'dodaj-kategoriju', component: AddKategorijaComponent /*canActivate:[AuthGuardRadnik]*/},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
