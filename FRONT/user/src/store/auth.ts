import { atom } from 'recoil';

export const authState = atom<string | null>({
  key: 'credential',
  default: localStorage.getItem('credential'),
});
