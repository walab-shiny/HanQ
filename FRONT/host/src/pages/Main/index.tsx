import { Box, Button } from '@mui/material';
import { useRecoilValue } from 'recoil';
import { getUserInfoByUserId } from '../../apis/auth';
import GoogleLoginBtn from '../../components/GoogleLoginBtn';
import LogoutBtn from '../../components/LogoutBtn';
import { authState } from '../../store/auth';

export default function Main() {
  const userId = useRecoilValue(authState);
  const handleAlertUserInfo = async () => {
    if (userId) {
      const response = await getUserInfoByUserId(userId);
      console.info(response);
      alert(response.name);
    } else {
      console.error('no userId.');
    }
  };

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', width: 200 }}>
      {userId ? (
        <>
          <Button
            onClick={() => {
              handleAlertUserInfo();
            }}
          >
            회원 정보 조회
          </Button>
          <LogoutBtn />
        </>
      ) : (
        <GoogleLoginBtn />
      )}
    </Box>
  );
}
