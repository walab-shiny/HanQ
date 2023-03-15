import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import Dashboard from './pages/Dashboard';
import Login from './pages/Login';
import { authState } from './store/auth';

function Router() {
  const userId = useRecoilValue(authState);
  return (
    <BrowserRouter>
      <Routes>
        {userId ? (
          <Route path="/" element={<Dashboard />} />
        ) : (
          <Route path="*" element={<Login />} />
        )}
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
