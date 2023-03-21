import {
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
} from '@mui/material';
import { useState } from 'react';

interface IEvent {
  category: string;
  name: string;
  location: string;
  content: string;
  open_at: Date;
  max_users: number;
  late_time: Date;
  image: string;
  cnt: number;
}

function createData(student_id: number, name: string, major: string) {
  return { student_id, name, major };
}

const rows = [
  createData(22000001, '김한동', '전산전자공학부'),
  createData(22000002, '김행복', '전산전자공학부'),
];

export default function EventInfo() {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <Box
      sx={{
        // width: '100%',
        bgcolor: 'background.paper',
        borderRadius: 3,
        p: 3,
      }}
    >
      <Box display="flex" justifyContent="space-between" alignItems="center">
        <Typography variant="h3" m={1} color="secondary">
          [4단계 BK21 AI사업단] 인공지능 워크샵 개최
        </Typography>
        <Button size="small" variant="contained" color="secondary" onClick={handleOpen}>
          명단 보기
        </Button>
        <Dialog open={open} onClose={handleClose} fullWidth>
          <DialogTitle>행사 참여자 명단</DialogTitle>
          <DialogContent>
            <TableContainer sx={{ maxHeight: 'calc(70vh)' }}>
              <Table sx={{ minWidth: 650 }} aria-label="simple table" stickyHeader>
                <TableHead>
                  <TableRow>
                    <TableCell>학번</TableCell>
                    <TableCell>이름</TableCell>
                    <TableCell>학부</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rows.map((row) => (
                    <TableRow
                      key={row.student_id}
                      sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                      <TableCell component="th" scope="row">
                        {row.student_id}
                      </TableCell>
                      <TableCell>{row.name}</TableCell>
                      <TableCell>{row.major}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose} variant="outlined">
              닫기
            </Button>
          </DialogActions>
        </Dialog>
      </Box>
      <Box bgcolor={'grey.200'} borderRadius={1} display="flex" gap={3} p={1} m={1}>
        <Typography variant="subtitle1">카테고리</Typography>
        <Typography variant="subtitle1">장소</Typography>
        <Typography variant="subtitle1">개최일</Typography>
        <Typography variant="subtitle1">최대 인원수</Typography>
        <Typography variant="subtitle1">지각허용시간</Typography>
        <Typography variant="subtitle1">수강인원</Typography>
      </Box>
      <Typography>2023년 한동대학교 BK21 AI사업단 인공지능 워크숍을 개최합니다.</Typography>

      <Typography variant="subtitle1">사진</Typography>
    </Box>
  );
}
