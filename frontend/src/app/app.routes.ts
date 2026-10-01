import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { Dashboard } from './pages/dashboard/dashboard';
import { Personnes } from './pages/personnes/personnes';
import { Commandes } from './pages/commandes/commandes';
import { Produits } from './pages/produits/produits';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
  { path: 'personnes', component: Personnes, canActivate: [authGuard] },
  { path: 'commandes', component: Commandes, canActivate: [authGuard] },
  { path: 'produits', component: Produits, canActivate: [authGuard] },
  { path: '**', redirectTo: 'login' }
];