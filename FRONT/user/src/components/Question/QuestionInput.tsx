import { Box, IconButton, InputBase, Paper } from '@mui/material';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import SendIcon from '@mui/icons-material/Send';

export default function QuestionInput() {
  return (
    <>
      <Paper
        component="form"
        sx={{
          p: '2px 4px',
          display: 'flex',
          alignItems: 'center',
          borderRadius: '5px',
          margin: 1,
          boxShadow: 1,
          width: 'calc(45vw)',
          height: 'calc(8vh)',
        }}
      >
        <Box sx={{ p: '10px' }} display="flex" justifyContent="center">
          <AccountCircleIcon />
        </Box>
        <InputBase sx={{ ml: 1, flex: 1 }} placeholder="질문을 입력하세요" />
        <IconButton type="button" sx={{ p: '10px' }}>
          <SendIcon />
        </IconButton>
      </Paper>
    </>
  );
}
