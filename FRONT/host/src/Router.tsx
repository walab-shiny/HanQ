import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Event from './pages/Event';
import EventDetail from './pages/EventDetail';
import Report from './pages/Report';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/event" element={<Event />} />
        <Route path="/event/detail" element={<EventDetail />} />
        <Route path="/report" element={<Report />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
