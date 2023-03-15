import NotificationsIcon from '@mui/icons-material/Notifications';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { Box, Typography } from '@mui/material';
import Search from './Search';

export default function Header() {
  return (
    <Box display="flex" alignItems="center" m={2} justifyContent="space-between">
      <Box display="flex" alignItems="center" ml={6} gap={1}>
        <Typography sx={{ fontSize: '1.2rem', fontWeight: 'bold' }}>대시보드</Typography>
        <Search />
      </Box>
      <Box display="flex" gap={3} mr={5}>
        <NotificationsIcon sx={{ color: 'gray' }} />
        <AccountCircleIcon />
      </Box>
    </Box>
  );
}
