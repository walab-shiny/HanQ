import { Box } from '@mui/material';
import Header from '../components/Dashboard/Header';
import Logo from '../components/Dashboard/Logo';
import MenuList from '../components/Dashboard/MenuList';
import QuestionInput from '../components/Question/QuestionInput';
import QuestionList from '../components/Question/QuestionList';

export default function Question() {
  return (
    <>
      <Box display="flex" height="100%">
        <Box display="flex" flexDirection="column" width={260} sx={{ bgcolor: 'background.paper' }}>
          <Logo />
          <MenuList page={3} />
        </Box>
        <Box display="flex" flexDirection="column" flexGrow={1} gap={1}>
          <Header page="질문하기" />
          <Box
            display="flex"
            justifyContent="center"
            flexDirection="column"
            alignItems="center"
            gap={2}
          >
            <QuestionInput />
            <QuestionList />
          </Box>
        </Box>
      </Box>
    </>
  );
}
