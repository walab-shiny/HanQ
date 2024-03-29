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
} from '@mui/material';
import ExportButton from '../Report/ExportButton';
import { useEffect, useState } from 'react';
import { IParticipant } from '../../types/participant';
import { getParticipantList } from '../../apis/participant';

interface Props {
  open: boolean;
  onClose: (value: void) => void;
  id: string;
}

export default function ParticipantDialog(props: Props) {
  const { onClose, open } = props;

  const handleClose = () => {
    onClose();
  };

  const [participants, setParticipants] = useState<IParticipant[]>();

  const fetchData = async () => {
    const response = await getParticipantList(props.id);
    setParticipants(response);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <Dialog open={open} onClose={handleClose} fullWidth>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <DialogTitle>행사 참여자 명단</DialogTitle>
          <Box mr={1}>
            <ExportButton data={participants} />
          </Box>
        </Box>
        <DialogContent>
          <TableContainer sx={{ maxHeight: 'calc(70vh)' }}>
            <Table stickyHeader>
              <TableHead>
                <TableRow>
                  <TableCell align="center">학번</TableCell>
                  <TableCell align="center">이름</TableCell>
                  <TableCell align="center">학부</TableCell>
                  <TableCell align="center">태깅시간</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {participants?.map((participant) => (
                  <TableRow
                    key={participant.studentNum}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                  >
                    <TableCell align="center" component="th" scope="row">
                      {participant.studentNum}
                    </TableCell>
                    <TableCell align="center">{participant.name}</TableCell>
                    <TableCell align="center">{participant.department}</TableCell>
                    <TableCell align="center">
                      {participant.taggedAt.split('T')[0]} {participant.taggedAt.split('T')[1]}
                    </TableCell>
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
    </>
  );
}
