import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-statistics',
  standalone: true,
  imports: [MatCardModule],
  template: `<h1>Estatísticas</h1><mat-card><mat-card-content>Métricas de candidaturas, taxa de resposta e tecnologias mais solicitadas.</mat-card-content></mat-card>`,
})
export class StatisticsComponent {}
