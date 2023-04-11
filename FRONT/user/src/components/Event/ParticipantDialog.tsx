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
import { Iparticipant } from '../../types/participant';
import { getParticipantList } from '../../apis/participant';

function createData(student_id: number, name: string, major: string) {
  return { student_id, name, major };
}

const rows = [
  createData(22000001, '김한동', '전산전자공학부'),
  createData(22000002, '김행복', '전산전자공학부'),
];

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

  const [participant, setParticipant] = useState<Iparticipant[]>();

  const fetchData = async () => {
    const response = await getParticipantList(props.id);
    setParticipant(response);
    console.log('participant');
    console.log(response);
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
            <ExportButton />
          </Box>
        </Box>
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
    </>
  );
}
