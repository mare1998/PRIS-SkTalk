import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KursItemComponent } from './kurs-item.component';

describe('KursItemComponent', () => {
  let component: KursItemComponent;
  let fixture: ComponentFixture<KursItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KursItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KursItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
