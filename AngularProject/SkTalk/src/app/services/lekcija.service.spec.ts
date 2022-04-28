import { TestBed } from '@angular/core/testing';

import { LekcijaService } from './lekcija.service';

describe('LekcijaService', () => {
  let service: LekcijaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LekcijaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
