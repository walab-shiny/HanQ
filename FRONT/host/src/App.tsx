import { SnackbarProvider } from 'notistack';
import ThemeProvider from './theme';
import Router from './Router';

function App() {
  return (
    <ThemeProvider>
      <SnackbarProvider maxSnack={3}>
        <Router />
      </SnackbarProvider>
    </ThemeProvider>
  );
}

export default App;
