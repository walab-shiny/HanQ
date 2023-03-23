import { GoogleOAuthProvider } from '@react-oauth/google';
import { SnackbarProvider } from 'notistack';
import ThemeProvider from './theme';
import Router from './Router';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { authState } from './store/auth';
import { useEffect, useState } from 'react';
import { getUserInfoByUserId, loginWithCredential } from './apis/auth';
import { userState } from './store/user';
import { Backdrop, CircularProgress } from '@mui/material';

function App() {
  const [init, setInit] = useState(false);
  const googleClientId = process.env.REACT_APP_GOOGLE_CLIENT_ID ?? '';
  const credential = useRecoilValue(authState);
  const setUser = useSetRecoilState(userState);

  useEffect(() => {
    const handleLogin = async () => {
      if (credential) {
        const response = await loginWithCredential(credential);
        const userId = response.userId;
        const userInfo = await getUserInfoByUserId(userId);
        setUser(userInfo);
      }
      setInit(true);
    };
    handleLogin();
  }, []);

  return (
    <GoogleOAuthProvider clientId={googleClientId}>
      <ThemeProvider>
        <SnackbarProvider maxSnack={3}>
          {init ? (
            <Router />
          ) : (
            <Backdrop
              sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
              open={true}
            >
              <CircularProgress color="inherit" />
            </Backdrop>
          )}
        </SnackbarProvider>
      </ThemeProvider>
    </GoogleOAuthProvider>
  );
}

export default App;
