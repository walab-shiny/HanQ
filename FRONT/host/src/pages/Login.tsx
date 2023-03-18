import { Box } from '@mui/material';
import GoogleLoginBtn from '../components/GoogleLoginBtn';
import ThemeModeToggle from '../components/Header/ThemeModeToggle';

export default function Login() {
  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        width: 1,
        height: 1,
      }}
    >
      <ThemeModeToggle />
      <GoogleLoginBtn />
    </Box>
  );
}
