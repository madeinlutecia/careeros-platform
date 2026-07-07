import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-diary',
  standalone: true,
  imports: [MatCardModule, MatButtonModule],
  template: `
    <h1>Diário de Carreira</h1>
    <mat-card>
      <mat-card-header>
        <mat-card-title>Diferencial CareerOS</mat-card-title>
      </mat-card-header>
      <mat-card-content>
        Registre em poucos minutos o que fez no trabalho. A IA transforma em bullets
        para currículo, atualizações do LinkedIn, material para entrevistas e conquistas.
      </mat-card-content>
      <mat-card-actions>
        <button mat-raised-button color="primary">Nova entrada</button>
        <button mat-button>Pesquisar histórico</button>
      </mat-card-actions>
    </mat-card>
  `,
})
export class DiaryComponent {}
