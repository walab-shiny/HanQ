import { Button } from '@mui/material';
import { googleLogout } from '@react-oauth/google';
import React from 'react';
import { useResetRecoilState } from 'recoil';
import { authState } from '../store/auth';
import { userState } from '../store/user';

interface Props {
  children: React.ReactNode;
}

export default function LogoutBtn({ children }: Props) {
  const resetCredential = useResetRecoilState(authState);
  const resetUser = useResetRecoilState(userState);
  const removeCredential = () => {
    localStorage.removeItem('credential');
    resetCredential();
    resetUser();
    window.location.reload();
  };
  const handleLogout = () => {
    googleLogout();
    removeCredential();
  };

  return (
    <Button variant="contained" color="error" onClick={handleLogout}>
      {children}
    </Button>
  );
}
