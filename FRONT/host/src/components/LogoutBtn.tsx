import { Button } from '@mui/material';
import { googleLogout } from '@react-oauth/google';
import { useResetRecoilState } from 'recoil';
import { authState } from '../store/auth';

export default function LogoutBtn() {
  const resetUserId = useResetRecoilState(authState);
  const handleLogout = () => {
    googleLogout();
    resetUserId();
  };
  return <Button onClick={handleLogout}>Logout</Button>;
}
