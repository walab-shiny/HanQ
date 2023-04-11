import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Button, Chip, Toolbar, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import AddEventDialog from './AddEventDialog';
import ReportDialog from '../Report/ReportDialog';
import { getEventList } from '../../apis/event';
import { IEvent } from '../../types/event';
import QRScan from '../../pages/QRScan';
import { useRecoilValue } from 'recoil';
import { userState } from '../../store/user';

export default function EventList() {
  const user = useRecoilValue(userState);
  const [eventList, setEventList] = useState<IEvent[]>([]);
  const navigate = useNavigate();

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

  return (
    <>
      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
        <Typography variant="subtitle1" m={1}>
          주최 이벤트 목록
        </Typography>
        {/* <Tooltip title="create event"> */}
        <AddEventDialog fetchData={fetchData} />
        {/* </Tooltip> */}
      </Toolbar>

      <TableContainer component={Paper} sx={{ maxHeight: 'calc(70vh)' }}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table" stickyHeader>
          <TableHead>
            <TableRow>
              <TableCell align="center">번호</TableCell>
              <TableCell align="center">태그</TableCell>
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
                // onClick={() => navigate(`/event/detail/${row.id}`)}
              >
                <TableCell
                  component="th"
                  scope="row"
                  align="center"
                  onClick={() => navigate(`/event/detail/${row.id}`)}
                >
                  {index + 1}
                </TableCell>
                <TableCell align="center" onClick={() => navigate(`/event/detail/${row.id}`)}>
                  {row.tags.map((tag) => (
                    <Chip key={tag.id} label={tag.name} sx={{ mr: 1 }} size="small" />
                  ))}
                </TableCell>
                <TableCell align="center" onClick={() => navigate(`/event/detail/${row.id}`)}>
                  {row.name}
                </TableCell>
                <TableCell align="center" onClick={() => navigate(`/event/detail/${row.id}`)}>
                  {row.openAt}
                </TableCell>
                <TableCell align="center" onClick={() => navigate(`/event/detail/${row.id}`)}>
                  {row.location}
                </TableCell>
                <TableCell align="center" onClick={() => navigate(`/event/detail/${row.id}`)}>
                  {row.maxUsers}
                </TableCell>
                <TableCell align="center">
                  <QRScan event={row} />
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
    </>
  );
}
