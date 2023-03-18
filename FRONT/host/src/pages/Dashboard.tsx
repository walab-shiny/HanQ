import { Box } from '@mui/material';
import CardList from '../components/Dashboard/CardList';
import ChartList from '../components/Dashboard/ChartList';
import Header from '../components/Dashboard/Header';
import Logo from '../components/Dashboard/Logo';
import MenuList from '../components/Dashboard/MenuList';

export default function Dashboard() {
  return (
    <Box display="flex" height="100%">
      <Box
        display="flex"
        flexDirection="column"
        width={'calc(18vw)'}
        sx={{ bgcolor: 'background.paper' }}
      >
        <Logo />
        <MenuList page={0} />
      </Box>
      <Box display="flex" flexDirection="column" flexGrow={1} gap={1}>
        <Header page={'대시보드'} />
        <Box display="flex" justifyContent="space-evenly">
          <CardList />
          <ChartList />
        </Box>
      </Box>
    </Box>
  );
}
