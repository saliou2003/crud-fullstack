import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const token = inject(Auth).getToken();

  if (token) {
    return true;
  }

  router.navigate(['/login']);
  return false;
};