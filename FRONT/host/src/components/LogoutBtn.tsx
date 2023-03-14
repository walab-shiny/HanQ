import { Button } from '@mui/material';
import { googleLogout } from '@react-oauth/google';

export default function LogoutBtn() {
  return <Button onClick={googleLogout}>Logout</Button>;
}
