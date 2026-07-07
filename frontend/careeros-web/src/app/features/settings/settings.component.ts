import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [MatCardModule],
  template: `<h1>Configurações</h1><mat-card><mat-card-content>OAuth2, preferências de IA e conectores de vagas.</mat-card-content></mat-card>`,
})
export class SettingsComponent {}
