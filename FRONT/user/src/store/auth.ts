import { atom } from 'recoil';

export const authState = atom<string | null>({
  key: 'credential',
  default: localStorage.getItem('credential'),
});

export const tokenState = atom<string | null>({
  key: 'token',
  default: localStorage.getItem('token'),
});
