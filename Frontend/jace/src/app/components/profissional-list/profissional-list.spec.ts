import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProfissionalList } from './profissional-list';

describe('ProfissionalList', () => {
  let component: ProfissionalList;
  let fixture: ComponentFixture<ProfissionalList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfissionalList],
    }).compileComponents();

    fixture = TestBed.createComponent(ProfissionalList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
