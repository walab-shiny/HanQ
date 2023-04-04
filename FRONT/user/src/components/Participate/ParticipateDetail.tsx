import { Box } from '@mui/material';
import Logo from '../Dashboard/Logo';
import MenuList from '../Dashboard/MenuList';
import Header from '../Dashboard/Header';
import ParticipateInfo from './ParticipateInfo';

export default function ParticipateDetail() {
  return (
    <>
      <Box display="flex" height="100%">
        <Box display="flex" flexDirection="column" sx={{ bgcolor: 'background.paper' }}>
          <Logo />
          <MenuList page={2} />
        </Box>
        <Box display="flex" flexDirection="column" flexGrow={1} gap={1}>
          <Header page="참여 행사 상세 조회" />
          <Box m={3} mt={1}>
            {/* <EventInfo /> */}
            <ParticipateInfo />
          </Box>
        </Box>
      </Box>
    </>
  );
}
