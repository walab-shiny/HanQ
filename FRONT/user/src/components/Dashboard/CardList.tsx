import Box from '@mui/material/Box';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Card from '@mui/material/Card';

export default function CardList() {
  const notis = [
    '3일 뒤 ‘ChatGPT 강연’ 행사가 예정되어 있습니다.',
    '이번 달에는 4개의 행사가 예정되어 있습니다.',
  ];
  return (
    <Box>
      {notis.map((noti, idx) => (
        <Box m={3} key={idx}>
          <Card>
            <CardContent>
              <Typography variant="h5" component="div">
                {noti}
              </Typography>
            </CardContent>
          </Card>
        </Box>
      ))}
    </Box>
  );
}
