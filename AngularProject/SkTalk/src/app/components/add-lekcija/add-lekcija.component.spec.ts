import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLekcijaComponent } from './add-lekcija.component';

describe('AddLekcijaComponent', () => {
  let component: AddLekcijaComponent;
  let fixture: ComponentFixture<AddLekcijaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLekcijaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLekcijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
