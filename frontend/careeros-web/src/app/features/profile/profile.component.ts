import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [MatCardModule],
  template: `
    <h1>Perfil Mestre</h1>
    <mat-card>
      <mat-card-content>
        Fonte única da verdade da sua carreira. Cadastre experiências, formação,
        certificações, competências e projetos para alimentar todos os agentes de IA.
      </mat-card-content>
    </mat-card>
  `,
})
export class ProfileComponent {}
