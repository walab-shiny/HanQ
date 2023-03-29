import { atom } from 'recoil';

export const userState = atom<boolean | null>({
  key: 'user',
  default: null,
});
