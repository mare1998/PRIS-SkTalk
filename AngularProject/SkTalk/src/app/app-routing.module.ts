import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddKategorijaComponent } from './components/add-kategorija/add-kategorija.component';
import { AddKursComponent } from './components/add-kurs/add-kurs.component';
import { AddLekcijaComponent } from './components/add-lekcija/add-lekcija.component';
import { AddPredavacComponent } from './components/add-predavac/add-predavac.component';
import { KursViewComponent } from './components/kurs-view/kurs-view.component';
import { KurseviComponent } from './components/kursevi/kursevi.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuardAdmin } from './guard/auth-guard-admin';
import { AuthGuardPredavac } from './guard/auth-guard-predavac';

const routes: Routes = [
  { path: '', redirectTo: 'svi-kursevi', pathMatch: 'full' },
  { path: 'svi-kursevi', component: KurseviComponent },
  {
    path: 'dodaj-kurs',
    component: AddKursComponent,
    canActivate: [AuthGuardAdmin],
  },
  {
    path: 'dodaj-predavaca',
    component: AddPredavacComponent,
    canActivate: [AuthGuardAdmin],
  },
  {
    path: 'dodaj-kategoriju',
    component: AddKategorijaComponent,
    canActivate: [AuthGuardAdmin],
  },
  { path: 'prikaz-kursa/:nazivKursa', component: KursViewComponent },
  { path: 'dodaj-lekciju', component: AddLekcijaComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
