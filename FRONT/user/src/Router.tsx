import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ThemeModeToggle from './components/Header/ThemeModeToggle';
import Main from './pages/Main';

function Router() {
  return (
    <BrowserRouter>
      <ThemeModeToggle />
      <Routes>
        <Route path="/" element={<Main />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
