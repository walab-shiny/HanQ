import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Button, IconButton, Toolbar, Typography } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import AddEventDialog from './AddEventDialog';
import ReportDialog from '../Report/ReportDialog';
import QRScan from '../../pages/QRScan';

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
  createData(6, '전산전자', '캡스톤페스티벌', '2023-03-16', 'NTH311', 543),
  createData(7, '전산전자', '공학프로젝트기획 페스티벌', '2023-03-16', 'NTH311', 412),
  createData(8, '전산전자', '전산전자 특강', '2023-03-26', 'NTH201', 64),
  createData(9, '전산전자', '공학인증의 밤', '2023-05-21', 'NTH311', 45),
  createData(10, '전산전자', '전전 개강예배', '2023-06-06', 'NTH311', 34),
  createData(11, '전산전자', '캡스톤페스티벌', '2023-03-16', 'NTH311', 543),
  createData(12, '전산전자', '공학프로젝트기획 페스티벌', '2023-03-16', 'NTH311', 412),
  createData(13, '전산전자', '전산전자 특강', '2023-03-26', 'NTH201', 64),
  createData(14, '전산전자', '공학인증의 밤', '2023-05-21', 'NTH311', 45),
  createData(15, '전산전자', '전전 개강예배', '2023-06-06', 'NTH311', 34),
];

export default function EventList() {
  const navigate = useNavigate();

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [reportOpen, setReportOpen] = useState(false);
  const handleReportOpen = () => setReportOpen(true);
  const handleReportClose = () => setReportOpen(false);

  const [QROpen, setQROpen] = useState(false);
  const handleQROpen = () => setQROpen(true);
  const handleQRClose = () => setQROpen(false);

  return (
    <>
      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
        <Typography variant="subtitle1" m={1}>
          주최 이벤트 목록
        </Typography>
        {/* <Tooltip title="create event"> */}
        <IconButton onClick={handleOpen}>
          <AddIcon />
        </IconButton>
        <AddEventDialog open={open} onClose={handleClose} />
        {/* </Tooltip> */}
      </Toolbar>

      <TableContainer component={Paper} sx={{ maxHeight: 'calc(70vh)' }}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table" stickyHeader>
          <TableHead>
            <TableRow>
              <TableCell align="center">번호</TableCell>
              <TableCell align="center">카테고리</TableCell>
              <TableCell align="center">제목</TableCell>
              <TableCell align="center">일자</TableCell>
              <TableCell align="center">장소</TableCell>
              <TableCell align="center">참여인원수</TableCell>
              <TableCell align="center">QR 스캔</TableCell>
              <TableCell align="center">소감문 확인</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <TableRow
                key={row.no}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                // onClick={() => navigate('/event/detail')}
              >
                <TableCell
                  component="th"
                  scope="row"
                  align="center"
                  onClick={() => navigate('/event/detail')}
                >
                  {row.no}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.category}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.title}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.date}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.location}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.cnt}
                </TableCell>
                <TableCell align="center" onClick={handleQROpen}>
                  <Button size="small" variant="contained" color="success">
                    QR 스캔
                  </Button>
                </TableCell>
                <TableCell align="center">
                  <Button
                    size="small"
                    variant="contained"
                    color="secondary"
                    onClick={handleReportOpen}
                  >
                    소감문 확인
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <ReportDialog open={reportOpen} onClose={handleReportClose} />
      <QRScan open={QROpen} onClose={handleQRClose} />
    </>
  );
}
