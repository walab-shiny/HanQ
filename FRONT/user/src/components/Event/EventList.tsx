import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Typography } from '@mui/material';

function createData(
  no: number,
  category: string,
  title: string,
  date: any,
  location: string,
  cnt: number,
) {
  return { no, category, title, date, location, cnt };
}

const rows = [
  createData(1, '전산전자', '캡스톤페스티벌', '2023-03-16', 'NTH311', 543),
  createData(2, '전산전자', '공학프로젝트기획 페스티벌', '2023-03-16', 'NTH311', 412),
  createData(3, '전산전자', '전산전자 특강', '2023-03-26', 'NTH201', 64),
  createData(4, '전산전자', '공학인증의 밤', '2023-05-21', 'NTH311', 45),
  createData(5, '전산전자', '전전 개강예배', '2023-06-06', 'NTH311', 34),
];

export default function EventList() {
  return (
    <TableContainer component={Paper}>
      <Typography variant="subtitle1" m={2}>
        주최 행사 목록
      </Typography>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>번호</TableCell>
            <TableCell>카테고리</TableCell>
            <TableCell>제목</TableCell>
            <TableCell>일자</TableCell>
            <TableCell>장소</TableCell>
            <TableCell>참여인원수</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow key={row.no} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
              <TableCell component="th" scope="row">
                {row.no}
              </TableCell>
              <TableCell>{row.category}</TableCell>
              <TableCell>{row.title}</TableCell>
              <TableCell>{row.date}</TableCell>
              <TableCell>{row.location}</TableCell>
              <TableCell>{row.cnt}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
