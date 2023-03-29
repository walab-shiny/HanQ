import Box from '@mui/material/Box';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Card from '@mui/material/Card';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './calendar-custom.css';
import { useTheme } from '@mui/material';

export default function CardList() {
  const { palette } = useTheme();
  const notis = [
    '3일 뒤 ‘ChatGPT 강연’ 이벤트가 예정되어 있습니다.',
    '이번 달에는 4개의 이벤트가 예정되어 있습니다.',
    '지금까지 개최한 이벤트는 12개입니다.',
  ];
  return (
    <Box display="flex" flexDirection="column" alignItems="center" gap={3} justifyContent="center">
      <Card sx={{ width: '100%' }}>
        <Typography
          variant="h5"
          m={3}
          pl={1}
          sx={{
            backgroundColor: palette.mode === 'light' ? '#ebff82' : 'primary.main',
            borderRadius: '5px',
          }}
        >
          Notice
        </Typography>
        {notis.map((noti, idx) => (
          <Box m={2} key={idx}>
            <Card sx={{ backgroundColor: 'rgba(114, 92, 255, 0.08)' }}>
              <CardContent>
                <Typography variant="subtitle1" component="div">
                  {noti}
                </Typography>
              </CardContent>
            </Card>
          </Box>
        ))}
      </Card>
      <Calendar minDetail="decade" prev2Label={null} next2Label={null} calendarType="US" />
    </Box>
  );
}
