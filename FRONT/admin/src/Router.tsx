import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import Login from './pages/Login';
import Main from './pages/Main';
import { userState } from './store/user';

function Router() {
  const user = useRecoilValue(userState);

  return (
    <BrowserRouter>
      <Routes>
        {user ? <Route path="/" element={<Main />} /> : <Route path="*" element={<Login />} />}
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
