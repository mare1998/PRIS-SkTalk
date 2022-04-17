import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KursViewComponent } from './kurs-view.component';

describe('KursViewComponent', () => {
  let component: KursViewComponent;
  let fixture: ComponentFixture<KursViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KursViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KursViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
