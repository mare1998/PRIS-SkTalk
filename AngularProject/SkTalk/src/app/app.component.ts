import { Component } from '@angular/core';
import { Kategorija } from './model/kategorija';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'SkTalk';

  public kategorije: Kategorija[] | undefined;
  
}
