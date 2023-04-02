import { Box, Button, Typography } from '@mui/material';
import RequestHostAuth from './RequestHostAuth';
import { useState } from 'react';

interface IUserInfo {
  user: any;
}

export default function Accepted(props: IUserInfo) {
  console.log(props.user);
  const [dialog, setDialog] = useState(false);

  const openDialog = () => setDialog(true);
  const closeDialog = () => setDialog(false);

  return (
    <>
      <Box
        width={'calc(35vw)'}
        sx={{ backgroundColor: 'background.paper', p: 5, borderRadius: 1, boxShadow: 3 }}
        display="flex"
        flexDirection="column"
        gap={2}
      >
        <Typography variant="h5" pb={1}>
          내 정보
        </Typography>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="subtitle1">이름</Typography>
          <Typography
            variant="subtitle1"
            sx={{ p: 0.5, pl: 2, pr: 2, borderRadius: 1, border: '1px solid ' }}
          >
            {props.user.name}
          </Typography>
        </Box>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="subtitle1">학번</Typography>
          <Typography
            variant="subtitle1"
            sx={{ p: 0.5, pl: 2, pr: 2, borderRadius: 1, border: '1px solid ' }}
          >
            {props.user.studentNum}
          </Typography>
        </Box>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="subtitle1">이메일</Typography>
          <Typography
            variant="subtitle1"
            sx={{ p: 0.5, pl: 2, pr: 2, borderRadius: 1, border: '1px solid ' }}
          >
            {props.user.email}
          </Typography>
        </Box>
        {props.user.isHost === true ? (
          <Box display="flex" justifyContent="space-between" alignItems="center">
            <Typography variant="subtitle1">권한 유효 기간</Typography>
            <Typography
              variant="subtitle1"
              sx={{ p: 0.5, pl: 2, pr: 2, borderRadius: 1, border: '1px solid' }}
            >
              {props.user.hostUntil}
            </Typography>
          </Box>
        ) : (
          <>
            <Button variant="contained">이벤트 주최 권한 신청</Button>
            <RequestHostAuth closeDialog={closeDialog} />
          </>
        )}

        {dialog === true ? (
          <Box display="flex" justifyContent="center">
            <RequestHostAuth closeDialog={closeDialog} />
          </Box>
        ) : (
          <>
            <Button variant="contained" onClick={openDialog}>
              이벤트 주최 권한 신청
            </Button>
          </>
        )}
      </Box>
    </>
  );
}
