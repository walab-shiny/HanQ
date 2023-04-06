import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import RegisterModal from './components/Register/RegisterModal';
import Dashboard from './pages/Dashboard';
import Event from './pages/Event';
import EventDetail from './pages/EventDetail';
import Login from './pages/Login';
import Question from './pages/Question';
import Setting from './pages/Setting';
import { userState } from './store/user';
import Participate from './pages/Participate';
import ParticipateDetail from './components/Participate/ParticipateDetail';
import QRScan from './pages/QRScan';

function Router() {
  const user = useRecoilValue(userState);
  const openRegisterModal = Boolean(user) && !user?.isRegistered;

  return (
    <BrowserRouter>
      <Routes>
        {user ? (
          <>
            <Route path="/" element={<Dashboard />} />
            <Route path="/event" element={<Event />} />
            <Route path="/event/detail" element={<EventDetail />} />
            <Route path="/participate" element={<Participate />} />
            <Route path="/participate/detail" element={<ParticipateDetail />} />
            <Route path="/question" element={<Question />} />
            <Route path="/setting" element={<Setting />} />
          </>
        ) : (
          <Route path="*" element={<Login />} />
        )}
      </Routes>
      <RegisterModal open={openRegisterModal} />
    </BrowserRouter>
  );
}

export default Router;
