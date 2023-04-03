import { Box, Button, TextField, Typography, useTheme } from '@mui/material';
import RequestHostAuth from './RequestHostAuth';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import TagSelect from './TagSelect';

interface IUserInfo {
  user: any;
}

export default function Accepted(props: IUserInfo) {
  const { palette } = useTheme();
  const { register, handleSubmit } = useForm({
    defaultValues: {
      name: props.user.name,
    },
  });

  // console.log(props.user);
  const [dialog, setDialog] = useState(false);

  const openDialog = () => setDialog(true);
  const closeDialog = () => setDialog(false);

  const onValid = (data: any) => {
    console.log(data);
  };

  return (
    <>
      <Box
        width={'calc(35vw)'}
        sx={{ backgroundColor: 'background.paper', p: 5, borderRadius: 1, boxShadow: 3 }}
        display="flex"
        flexDirection="column"
        gap={2}
      >
        <Typography
          variant="h5"
          mb={2}
          p={1}
          sx={{
            backgroundColor: palette.mode === 'light' ? '#ebff82' : 'primary.main',
            borderRadius: '5px',
          }}
        >
          내 정보
        </Typography>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="subtitle1">이름</Typography>
          <TextField
            size="small"
            {...register('name')}
            inputProps={{ style: { textAlign: 'end' } }}
          />
        </Box>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="subtitle1">학번</Typography>
          <TextField
            size="small"
            value={props.user.studentNum}
            disabled
            inputProps={{ style: { textAlign: 'end' } }}
          />
        </Box>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="subtitle1">이메일</Typography>
          <TextField
            sx={{ width: '55%' }}
            size="small"
            value={props.user.email}
            disabled
            inputProps={{ style: { textAlign: 'end' } }}
          />
        </Box>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="subtitle1">관심 등록 태그</Typography>
          {/* <TextField
            size="small"
            value={'전산전자공학부'}
            inputProps={{ style: { textAlign: 'end' } }}
          /> */}
          <TagSelect />
        </Box>
        {props.user.isPending === true ? (
          <Box display="flex" justifyContent="center">
            <Typography variant="subtitle1">권한 신청이 완료되었습니다</Typography>
          </Box>
        ) : (
          <>
            {props.user.isHost === true ? (
              <Box display="flex" justifyContent="space-between" alignItems="center">
                <Typography variant="subtitle1">권한 유효 기간</Typography>
                <TextField
                  size="small"
                  value={props.user.hostUntil}
                  disabled
                  inputProps={{ style: { textAlign: 'end' } }}
                />
              </Box>
            ) : (
              <>
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
              </>
            )}
          </>
        )}
        <Button variant="contained" sx={{ mt: 2 }} type="submit" onClick={handleSubmit(onValid)}>
          수정하기
        </Button>
      </Box>
    </>
  );
}
