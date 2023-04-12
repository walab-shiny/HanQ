import { Box } from '@mui/material';
import Logo from '../components/Dashboard/Logo';
import MenuList from '../components/Dashboard/MenuList';
import Header from '../components/Dashboard/Header';
import ParticipateList from '../components/Participate/ParticipateList';

export default function Participate() {
  return (
    <>
      <Box display="flex" height="100%">
        <Box display="flex" flexDirection="column" width={260} sx={{ bgcolor: 'background.paper' }}>
          <Logo />
          <MenuList page={2} />
        </Box>
        <Box display="flex" flexDirection="column" flexGrow={1} gap={1}>
          <Header page="참여 이벤트 확인" />
          <Box m={3} mt={1}>
            <ParticipateList />
          </Box>
        </Box>
      </Box>
    </>
  );
}
