import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-jobs',
  standalone: true,
  imports: [MatCardModule],
  template: `<h1>Vagas</h1><mat-card><mat-card-content>Rastreador de vagas com análise de compatibilidade por IA.</mat-card-content></mat-card>`,
})
export class JobsComponent {}
