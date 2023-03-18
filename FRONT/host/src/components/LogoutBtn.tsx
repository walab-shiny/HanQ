import { Button } from '@mui/material';
import { googleLogout } from '@react-oauth/google';
import { useResetRecoilState } from 'recoil';
import { authState } from '../store/auth';

export default function LogoutBtn() {
  const resetCredential = useResetRecoilState(authState);
  const removeCredential = () => {
    localStorage.removeItem('credential');
    resetCredential();
  };
  const handleLogout = () => {
    googleLogout();
    removeCredential();
  };
  return <Button onClick={handleLogout}>Logout</Button>;
}
