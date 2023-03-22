import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import Dashboard from './pages/Dashboard';
import Event from './pages/Event';
import EventDetail from './pages/EventDetail';
import Login from './pages/Login';
import { authState } from './store/auth';

function Router() {
  const credential = useRecoilValue(authState);
  return (
    <BrowserRouter>
      <Routes>
        {credential ? (
          <>
            <Route path="/" element={<Dashboard />} />
            <Route path="/event" element={<Event />} />
            <Route path="/event/detail" element={<EventDetail />} />
          </>
        ) : (
          <Route path="*" element={<Login />} />
        )}
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
