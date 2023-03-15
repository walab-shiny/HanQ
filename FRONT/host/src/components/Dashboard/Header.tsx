import NotificationsIcon from '@mui/icons-material/Notifications';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { Box, Button, Typography } from '@mui/material';
import Search from './Search';
import { useRecoilValue } from 'recoil';
import { userState } from '../../store/user';
import LogoutBtn from '../LogoutBtn';
import ThemeModeToggle from '../Header/ThemeModeToggle';

export default function Header() {
  const user = useRecoilValue(userState);
  const handleAlertUserInfo = async () => {
    if (user) {
      console.info(user);
      alert(`이름: ${user.name}\n이메일: ${user.email}`);
    }
  };
  return (
    <Box display="flex" alignItems="center" m={2} justifyContent="space-between">
      <Box display="flex" alignItems="center" ml={6} gap={1}>
        <Typography sx={{ fontSize: '1.2rem', fontWeight: 'bold' }}>대시보드</Typography>
        <Search />
      </Box>
      <Box display="flex" gap={2} mr={5}>
        {/* <NotificationsIcon />
        <AccountCircleIcon /> */}
        <ThemeModeToggle />
        <Button
          onClick={() => {
            handleAlertUserInfo();
          }}
        >
          회원 정보 조회
        </Button>
        <LogoutBtn />
      </Box>
    </Box>
  );
}
