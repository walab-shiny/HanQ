import Paper from '@mui/material/Paper';
import InputBase from '@mui/material/InputBase';
import IconButton from '@mui/material/IconButton';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import TagIcon from '@mui/icons-material/Tag';
import { Box, TextField, Typography } from '@mui/material';

export default function SearchBox() {
  return (
    <>
      <Box
        display="flex"
        alignItems="center"
        gap={1}
        sx={{ backgroundColor: 'info.darker', borderRadius: 5, width: 'calc(33vw)' }}
      >
        <Typography variant="h5" color={'white'} pl={3}>
          참여 코드가 있나요?
        </Typography>

        <Paper
          component="form"
          sx={{
            p: '2px 4px',
            display: 'flex',
            alignItems: 'center',
            // width: 400,
            borderRadius: '25px',
            margin: 1,
          }}
        >
          <IconButton sx={{ p: '10px' }} aria-label="menu">
            <TagIcon />
          </IconButton>
          <InputBase sx={{ ml: 1, flex: 1 }} placeholder="코드를 입력하세요" />
          <IconButton type="button" sx={{ p: '10px' }} aria-label="search">
            <ArrowForwardIcon />
          </IconButton>
        </Paper>
      </Box>
    </>
  );
}
