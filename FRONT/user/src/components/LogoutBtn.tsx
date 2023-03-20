import { Button } from '@mui/material';
import { googleLogout } from '@react-oauth/google';
import { useResetRecoilState } from 'recoil';
import { authState } from '../store/auth';
import { userState } from '../store/user';

export default function LogoutBtn() {
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
  return <Button onClick={handleLogout}>Logout</Button>;
}
