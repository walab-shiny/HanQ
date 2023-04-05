import { Box, Button, Typography } from '@mui/material';
import Search from './Search';
import { useRecoilValue } from 'recoil';
import { userState } from '../../store/user';
import LogoutBtn from '../LogoutBtn';
import ThemeModeToggle from '../Header/ThemeModeToggle';

interface IHeader {
  page: string;
}

export default function Header(props: IHeader) {
  const user = useRecoilValue(userState);
  const handleAlertUserInfo = async () => {
    if (user) {
      console.info(user);
      alert(`이름: ${user.name}\n이메일: ${user.email}`);
    }
  };
  return (
    <Box display="flex" alignItems="center" m={2} mb={1} justifyContent="space-between">
      <Box display="flex" alignItems="center" ml={6} gap={1}>
        <Typography sx={{ fontSize: '1.2rem', fontWeight: 'bold' }}>{props.page}</Typography>
        <Search />
      </Box>
      <Box display="flex" gap={2} mr={5} alignItems="center">
        {/* <NotificationsIcon />
        <AccountCircleIcon /> */}
        <ThemeModeToggle />
        <Typography variant="subtitle1">Hello {user?.name}</Typography>
        <LogoutBtn>로그아웃</LogoutBtn>
      </Box>
    </Box>
  );
}
