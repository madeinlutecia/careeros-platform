import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-linkedin',
  standalone: true,
  imports: [MatCardModule],
  template: `<h1>LinkedIn</h1><mat-card><mat-card-content>Sugestões de headline, sobre e experiências com SEO. Sem publicação automática.</mat-card-content></mat-card>`,
})
export class LinkedinComponent {}
