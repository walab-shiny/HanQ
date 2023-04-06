import { useTheme } from '@mui/material';
import { GoogleLogin } from '@react-oauth/google';
import { useSetRecoilState } from 'recoil';
import { getUserInfoByUserId, loginWithCredential } from '../apis/auth';
import { authState } from '../store/auth';
import { userState } from '../store/user';

export default function GoogleLoginBtn() {
  const setCredential = useSetRecoilState(authState);
  const setUser = useSetRecoilState(userState);
  const {
    palette: { mode },
  } = useTheme();
  const saveAuth = (credential: string, token: string) => {
    localStorage.setItem('credential', credential);
    localStorage.setItem('token', token);
    setCredential(credential);
  };
  const handleLogin = async (credential?: string) => {
    if (credential) {
      const response = await loginWithCredential(credential);
      const userId = response.userId;
      const userInfo = await getUserInfoByUserId(userId);
      const token = userInfo.token;
      saveAuth(credential, token);
      setUser(userInfo);
    } else {
      console.error('no credential');
    }
  };

  return (
    <GoogleLogin
      onSuccess={(credentialResponse) => handleLogin(credentialResponse.credential)}
      onError={() => {
        console.log('Login Failed');
      }}
      useOneTap
      theme={mode === 'dark' ? 'filled_black' : 'outline'}
    />
  );
}
