import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProfissionalDetails } from './profissional-details';

describe('ProfissionalDetails', () => {
  let component: ProfissionalDetails;
  let fixture: ComponentFixture<ProfissionalDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfissionalDetails],
    }).compileComponents();

    fixture = TestBed.createComponent(ProfissionalDetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
