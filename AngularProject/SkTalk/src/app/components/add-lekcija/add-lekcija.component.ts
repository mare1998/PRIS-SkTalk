import { HttpEventType } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ɵInternalFormsSharedModule } from '@angular/forms';
import { Kurs } from 'src/app/model/kurs';
import { Lekcija } from 'src/app/model/lekcija';
import { KursService } from 'src/app/services/kurs.service';
import { LekcijaService } from 'src/app/services/lekcija.service';

@Component({
  selector: 'app-add-lekcija',
  templateUrl: './add-lekcija.component.html',
  styleUrls: ['./add-lekcija.component.css'],
})
export class AddLekcijaComponent implements OnInit {
  @ViewChild('fileUpload', { static: false }) fileUpload: ElementRef;
  files = [];
  fileName: string;

  public idKurs: string;
  public kursevi: Kurs[];
  public lekcija: Lekcija;
  public podaci: FormData;

  constructor(
    private kursService: KursService,
    private lekcijaService: LekcijaService
  ) {
    this.lekcija = new Lekcija();
    this.podaci = new FormData();
    this.fileName = 'Click me to chose image!';
  }

  ngOnInit(): void {
    this.kursService.getKursevi().subscribe((resp: any) => {
      this.kursevi = resp;
    });
  }

  // public onImageUpload(files: FileList) {
  //   this.slika = files.item(0);
  //   console.log('Tip slike: ' + typeof this.slika);
  // }

  uploadFile(file: any) {
    this.podaci.append('slika', file.data);
    file.inProgress = true;
    console.log('krajj ' + this.podaci);
  }

  private uploadFiles() {
    this.fileUpload.nativeElement.value = '';
    this.files.forEach((file) => {
      this.uploadFile(file);
    });
  }

  public onClick() {
    const fileUpload = this.fileUpload.nativeElement;
    fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++) {
        const file = fileUpload.files[index];
        this.fileName = file.name;

        this.files.push({ data: file, inProgress: false, progress: 0 });
      }
      this.uploadFiles();
    };

    // fileUpload.click();
  }

  dodajNovuLekciju(lekcijaForm: any) {
    console.log(lekcijaForm.value);
    console.log(this.podaci.get('slika'));
    this.podaci.append('tekst', lekcijaForm.value.tekst);
    this.podaci.append('url_videa', lekcijaForm.value.url_videa);
    this.podaci.append('idKurs', lekcijaForm.value.idKurs);
    console.log(this.podaci);

    this.lekcijaService.dodajNovuLekciju(this.podaci).subscribe((event) => {
      alert('Uspesno dodata lekcija!');
      window.location.reload();
    });
  }
}
