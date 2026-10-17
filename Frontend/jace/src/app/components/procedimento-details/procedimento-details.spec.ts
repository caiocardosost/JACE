import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProcedimentoDetails } from './procedimento-details';

describe('ProcedimentoDetails', () => {
  let component: ProcedimentoDetails;
  let fixture: ComponentFixture<ProcedimentoDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProcedimentoDetails],
    }).compileComponents();

    fixture = TestBed.createComponent(ProcedimentoDetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
