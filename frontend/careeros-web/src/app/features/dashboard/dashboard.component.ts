import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [MatCardModule, MatGridListModule],
  template: `
    <h1>Dashboard Executivo</h1>
    <mat-grid-list cols="4" rowHeight="120px" gutterSize="16px">
      @for (metric of metrics; track metric.label) {
        <mat-grid-tile>
          <mat-card class="metric-card">
            <mat-card-title>{{ metric.value }}</mat-card-title>
            <mat-card-content>{{ metric.label }}</mat-card-content>
          </mat-card>
        </mat-grid-tile>
      }
    </mat-grid-list>
  `,
  styles: [`
    .metric-card { width: 100%; height: 100%; padding: 8px; box-sizing: border-box; }
    mat-card-title { font-size: 2rem; }
  `],
})
export class DashboardComponent {
  metrics = [
    { label: 'Vagas encontradas', value: '0' },
    { label: 'Candidaturas', value: '0' },
    { label: 'Entrevistas', value: '0' },
    { label: 'Taxa de conversão', value: '0%' },
    { label: 'Compatibilidade média', value: '0%' },
    { label: 'Favoritas', value: '0' },
    { label: 'Propostas', value: '0' },
    { label: 'Recusas', value: '0' },
  ];
}
