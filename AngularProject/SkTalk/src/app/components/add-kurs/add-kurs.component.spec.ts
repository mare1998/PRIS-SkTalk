import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKursComponent } from './add-kurs.component';

describe('AddKursComponent', () => {
  let component: AddKursComponent;
  let fixture: ComponentFixture<AddKursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddKursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddKursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
