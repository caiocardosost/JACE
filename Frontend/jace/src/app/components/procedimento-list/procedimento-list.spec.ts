import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProcedimentoList } from './procedimento-list';

describe('ProcedimentoList', () => {
  let component: ProcedimentoList;
  let fixture: ComponentFixture<ProcedimentoList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProcedimentoList],
    }).compileComponents();

    fixture = TestBed.createComponent(ProcedimentoList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
