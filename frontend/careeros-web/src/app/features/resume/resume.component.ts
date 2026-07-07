import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-resume',
  standalone: true,
  imports: [MatCardModule],
  template: `<h1>Gerador de Currículos</h1><mat-card><mat-card-content>Gere currículos personalizados por vaga com IA.</mat-card-content></mat-card>`,
})
export class ResumeComponent {}
