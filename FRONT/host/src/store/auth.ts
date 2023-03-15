import { atom } from 'recoil';

export const authState = atom<number | null>({
  key: 'auth',
  default: null,
});
