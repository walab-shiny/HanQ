import { SnackbarProvider } from 'notistack';
import ThemeProvider from './theme';
import Router from './Router';
import { useEffect, useState } from 'react';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { authState } from './store/auth';
import { userState } from './store/user';
import { Backdrop, CircularProgress } from '@mui/material';

function App() {
  const [init, setInit] = useState(false);
  const credential = useRecoilValue(authState);
  const setUser = useSetRecoilState(userState);

  useEffect(() => {
    const handleLogin = async () => {
      if (credential) {
        setUser(true);
      }
      setInit(true);
    };
    handleLogin();
  }, []);

  return (
    <ThemeProvider>
      <SnackbarProvider maxSnack={3}>
        {init ? (
          <Router />
        ) : (
          <Backdrop sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }} open={true}>
            <CircularProgress color="inherit" />
          </Backdrop>
        )}
      </SnackbarProvider>
    </ThemeProvider>
  );
}

export default App;
