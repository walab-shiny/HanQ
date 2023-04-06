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
import { useEffect, useState } from 'react';
import AddEventDialog from './AddEventDialog';
import ReportDialog from '../Report/ReportDialog';
import { getEventList } from '../../apis/event';
import { IEvent } from '../../types/event';
import QRScan from '../../pages/QRScan';

export default function EventList() {
  const [eventList, setEventList] = useState<IEvent[]>([]);
  const navigate = useNavigate();

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [reportOpen, setReportOpen] = useState(false);
  const handleReportOpen = () => setReportOpen(true);
  const handleReportClose = () => setReportOpen(false);

  const fetchData = async () => {
    const response = await getEventList();
    setEventList(response);
  };

  useEffect(() => {
    fetchData();
  }, []);
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
        <AddEventDialog open={open} onClose={handleClose} fetchData={fetchData} />
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
            {eventList.map((row, index) => (
              <TableRow
                key={row.id}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                // onClick={() => navigate('/event/detail')}
              >
                <TableCell
                  component="th"
                  scope="row"
                  align="center"
                  onClick={() => navigate('/event/detail')}
                >
                  {index + 1}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.tags.map((tag) => (
                    <span key={tag.id} style={{ marginRight: 2 }}>
                      {tag.name}
                    </span>
                  ))}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.name}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.openAt}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.location}
                </TableCell>
                <TableCell align="center" onClick={() => navigate('/event/detail')}>
                  {row.maxUsers}
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
