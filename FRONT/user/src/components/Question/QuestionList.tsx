import { Box } from '@mui/material';
import QuestionCard from './QuestionCard';

export default function QuestionList() {
  return (
    <Box
    // display="flex" justifyContent="center" flexDirection="column" gap={1.3}
    >
      <Box height={'calc(75vh)'} overflow={'scroll'}>
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
      </Box>
    </Box>
  );
}
