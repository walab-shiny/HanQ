import { GoogleLogin } from '@react-oauth/google';
import { useSetRecoilState } from 'recoil';
import { loginWithCredential } from '../apis/auth';
import { authState } from '../store/auth';

export default function GoogleLoginBtn() {
  const setUserId = useSetRecoilState(authState);
  const handleLogin = async (credential?: string) => {
    if (credential) {
      const response = await loginWithCredential(credential);
      setUserId(response.data.userId);
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
    />
  );
}
