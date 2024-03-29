import { Card, CardContent, ListItemButton, Typography, useTheme } from '@mui/material';
import { useState } from 'react';
import Apex from './Apex';

export default function ChartList() {
  const dataNames = [
    '참석자 수 증가율',
    '이번 달 이벤트 참석자 수',
    '방명록 작성 증가율',
    '방명록 작성률',
  ];

  const [data, setData] = useState([5, 15, 13, 15]);

  const [selectedIndex, setSelectedIndex] = useState(0);

  const handleListItemClick = (
    event: React.MouseEvent<HTMLDivElement, MouseEvent>,
    index: number,
  ) => {
    setSelectedIndex(index);
  };

  const { palette } = useTheme();

  return (
    <>
      <Card sx={{ mt: 3, width: '38%' }}>
        <CardContent>
          <Typography
            variant="h5"
            component="div"
            pl={1}
            sx={{
              backgroundColor: palette.mode === 'light' ? '#ebff82' : 'primary.main',
              borderRadius: '5px',
            }}
          >
            참석자 분석
          </Typography>
          {dataNames.map((dataName, idx) => (
            <Card key={idx} sx={{ mt: 2 }}>
              <ListItemButton
                selected={selectedIndex === idx}
                onClick={(event) => handleListItemClick(event, idx)}
              >
                <Apex />
                <CardContent>
                  <Typography sx={{ mb: 1, fontWeight: 'bold' }}>{dataName}</Typography>
                  <Typography variant="body2">{data[idx]} Lessons</Typography>
                </CardContent>
              </ListItemButton>
            </Card>
          ))}
        </CardContent>
      </Card>
    </>
  );
}
