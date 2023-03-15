import { GoogleLogin } from '@react-oauth/google';
import { loginWithCredential } from '../apis/auth';

export default function GoogleLoginBtn() {
  const handleLogin = (credential?: string) => {
    if (credential) {
      console.log(credential);
      console.log(loginWithCredential(credential));
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
