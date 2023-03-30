import Paper from '@mui/material/Paper';
import InputBase from '@mui/material/InputBase';
import IconButton from '@mui/material/IconButton';
import SearchIcon from '@mui/icons-material/Search';

export default function Search() {
  return (
    <>
      <Paper
        component="form"
        sx={{
          p: '2px 4px',
          ml: 15,
          display: 'flex',
          alignItems: 'center',
          width: 'calc(30vw)',
        }}
      >
        <IconButton type="button" sx={{ p: '10px' }} aria-label="search">
          <SearchIcon />
        </IconButton>
        <InputBase sx={{ ml: 1, flex: 1 }} placeholder="참여 코드를 입력하세요" />
      </Paper>
    </>
  );
}
