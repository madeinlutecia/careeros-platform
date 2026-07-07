import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
  ],
  template: `
    <mat-toolbar color="primary">
      <button mat-icon-button (click)="sidenav.toggle()">
        <mat-icon>menu</mat-icon>
      </button>
      <span>CareerOS Platform</span>
      <span class="spacer"></span>
      <small>Sistema Operacional da Carreira</small>
    </mat-toolbar>

    <mat-sidenav-container class="container">
      <mat-sidenav #sidenav mode="side" opened>
        <mat-nav-list>
          @for (item of navItems; track item.path) {
            <a mat-list-item [routerLink]="item.path" routerLinkActive="active">
              <mat-icon matListItemIcon>{{ item.icon }}</mat-icon>
              <span matListItemTitle>{{ item.label }}</span>
            </a>
          }
        </mat-nav-list>
      </mat-sidenav>
      <mat-sidenav-content>
        <main class="content">
          <router-outlet />
        </main>
      </mat-sidenav-content>
    </mat-sidenav-container>
  `,
  styles: [`
    .spacer { flex: 1; }
    .container { height: calc(100vh - 64px); }
    .content { padding: 24px; }
    .active { background: rgba(63, 81, 181, 0.08); }
  `],
})
export class AppComponent {
  navItems = [
    { path: '/dashboard', label: 'Dashboard', icon: 'dashboard' },
    { path: '/profile', label: 'Perfil Mestre', icon: 'person' },
    { path: '/diary', label: 'Diário de Carreira', icon: 'edit_note' },
    { path: '/jobs', label: 'Vagas', icon: 'work' },
    { path: '/resume', label: 'Currículo', icon: 'description' },
    { path: '/linkedin', label: 'LinkedIn', icon: 'share' },
    { path: '/statistics', label: 'Estatísticas', icon: 'analytics' },
    { path: '/settings', label: 'Configurações', icon: 'settings' },
  ];
}
