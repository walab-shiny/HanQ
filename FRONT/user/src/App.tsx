import { GoogleOAuthProvider } from '@react-oauth/google';
import { SnackbarProvider } from 'notistack';
import ThemeProvider from './theme';
import Router from './Router';

function App() {
  const googleClientId = process.env.REACT_APP_GOOGLE_CLIENT_ID ?? '';

  return (
    <GoogleOAuthProvider clientId={googleClientId}>
      <ThemeProvider>
        <SnackbarProvider maxSnack={3}>
          <Router />
        </SnackbarProvider>
      </ThemeProvider>
    </GoogleOAuthProvider>
  );
}

export default App;
