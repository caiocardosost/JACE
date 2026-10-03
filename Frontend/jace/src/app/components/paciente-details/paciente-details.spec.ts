import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PacienteDetails } from './paciente-details';

describe('PacienteDetails', () => {
  let component: PacienteDetails;
  let fixture: ComponentFixture<PacienteDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PacienteDetails],
    }).compileComponents();

    fixture = TestBed.createComponent(PacienteDetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
