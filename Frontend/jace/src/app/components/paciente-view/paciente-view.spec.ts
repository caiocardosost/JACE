import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PacienteView } from './paciente-view';

describe('PacienteView', () => {
  let component: PacienteView;
  let fixture: ComponentFixture<PacienteView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PacienteView],
    }).compileComponents();

    fixture = TestBed.createComponent(PacienteView);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
