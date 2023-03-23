import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import RegisterModal from './components/Register/RegisterModal';
import Dashboard from './pages/Dashboard';
import Event from './pages/Event';
import EventDetail from './pages/EventDetail';
import Login from './pages/Login';
// import Test from './pages/Test';
import { userState } from './store/user';

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
            {/* <Route path="/test" element={<Test />} /> */}
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
