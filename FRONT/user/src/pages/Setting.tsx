import { Box } from '@mui/material';
import { useRecoilValue } from 'recoil';
import Header from '../components/Dashboard/Header';
import Logo from '../components/Dashboard/Logo';
import MenuList from '../components/Dashboard/MenuList';
import { userState } from '../store/user';
import Status from '../components/Setting/Status';

export default function Setting() {
  const user = useRecoilValue(userState);

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
          <MenuList page={4} />
        </Box>
        <Box display="flex" flexDirection="column" flexGrow={1} gap={10}>
          <Header page="프로필 설정" />
          <Box>
            <Box display="flex" flexDirection="column" alignItems="center">
              <Status user={user} />
            </Box>
          </Box>
        </Box>
      </Box>
    </>
  );
}
