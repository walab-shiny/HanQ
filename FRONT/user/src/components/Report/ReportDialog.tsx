import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from '@mui/material';

function createData(no: number, name: string, content: string, modified_at: any) {
  return { no, name, content, modified_at };
}

const rows = [
  createData(
    1,
    '김한동',
    '실제 개발자들이 어떻게 일하는 지에 대해 알 수 있는 시간이었습니다.',
    '2023-03-16',
  ),
  createData(
    2,
    '김한동',
    '실제 개발자들이 어떻게 일하는 지에 대해 알 수 있는 시간이었습니다.',
    '2023-03-16',
  ),
  createData(
    3,
    '김한동',
    '실제 개발자들이 어떻게 일하는 지에 대해 알 수 있는 시간이었습니다.',
    '2023-03-16',
  ),
];

interface Props {
  open: boolean;
  onClose: (value: void) => void;
}

export default function ReportDialog(props: Props) {
  const { onClose, open } = props;

  const handleClose = () => {
    onClose();
  };

  return (
    <>
      <Dialog onClose={handleClose} open={open} fullWidth maxWidth="lg">
        <DialogTitle>보고서 목록</DialogTitle>
        <DialogContent>
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table" stickyHeader>
              <TableHead>
                <TableRow>
                  <TableCell align="center">번호</TableCell>
                  <TableCell align="center">이름</TableCell>
                  <TableCell align="center">내용</TableCell>
                  <TableCell align="center">수정일</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.no} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                    <TableCell component="th" scope="row" align="center">
                      {row.no}
                    </TableCell>
                    <TableCell align="center">{row.name}</TableCell>
                    <TableCell align="center">{row.content}</TableCell>
                    <TableCell align="center">{row.modified_at}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </DialogContent>
        <DialogActions>
          {/* <Button onClick={handleClose} variant="outlined">
            확인
          </Button> */}
          <Button onClick={handleClose} variant="contained" sx={{ fontWeight: 'bold' }}>
            확인
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
