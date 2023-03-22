import { Box } from '@mui/material';
import Header from '../components/Dashboard/Header';
import Logo from '../components/Dashboard/Logo';
import MenuList from '../components/Dashboard/MenuList';
import EventList from '../components/Event/EventList';

export default function Event() {
  return (
    <>
      <Box display="flex" height="100%">
        <Box
          display="flex"
          flexDirection="column"
          width={'calc(18vw)'}
          sx={{ bgcolor: 'background.paper' }}
        >
          <Logo />
          <MenuList page={1} />
        </Box>
        <Box display="flex" flexDirection="column" flexGrow={1} gap={1}>
          <Header page="행사 목록 관리" />
          <Box m={3} mt={1}>
            <EventList />
          </Box>
        </Box>
      </Box>
    </>
  );
}
