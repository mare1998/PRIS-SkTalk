import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPredavacComponent } from './add-predavac.component';

describe('AddPredavacComponent', () => {
  let component: AddPredavacComponent;
  let fixture: ComponentFixture<AddPredavacComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPredavacComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPredavacComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
