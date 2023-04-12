import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TableBody,
  TableContainer,
} from '@mui/material';
import { useState } from 'react';
import { IUser } from '../../pages/Main';
import { IconButton, Table, TableCell, TableRow } from '@mui/material';
import { InfoIcon } from '../../theme/overrides/CustomIcons';

interface IProps {
  user: IUser;
}

export default function UserInfoModal({ user }: IProps) {
  const [open, setOpen] = useState(false);
  const handleClickOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <>
      <IconButton onClick={handleClickOpen}>
        <InfoIcon />
      </IconButton>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>권한 요청을 수락하시겠습니까?</DialogTitle>
        <DialogContent sx={{ width: 400, maxWidth: 1 }}>
          <TableContainer>
            <Table>
              <TableBody>
                <TableRow>
                  <TableCell variant="footer">이름</TableCell>
                  <TableCell>{user.name}</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell variant="footer">이메일</TableCell>
                  <TableCell>{user.email}</TableCell>
                </TableRow>
                {user.isStudent ? (
                  <>
                    <TableRow>
                      <TableCell variant="footer">학부</TableCell>
                      <TableCell>{user.department}</TableCell>
                    </TableRow>
                    <TableRow>
                      <TableCell variant="footer">학번</TableCell>
                      <TableCell>{user.studentNum}</TableCell>
                    </TableRow>
                  </>
                ) : (
                  <TableRow>
                    <TableCell variant="footer">소속</TableCell>
                    <TableCell>{user.affiliation}</TableCell>
                  </TableRow>
                )}
                <TableRow>
                  <TableCell variant="footer">권한 마감 일자</TableCell>
                  <TableCell>{user.hostUntil || '없음'}</TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </TableContainer>
        </DialogContent>
        <DialogActions>
          <Button variant="contained" onClick={handleClose}>
            닫기
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
