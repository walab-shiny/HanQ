import { Box, Button, Card, InputLabel, TextField } from '@mui/material';
import { useSnackbar } from 'notistack';
import { useForm } from 'react-hook-form';
import { useRecoilState } from 'recoil';
import { getUserInfoByUserId } from '../../apis/auth';
import { requestHostAuth } from '../../apis/request';
import { userState } from '../../store/user';

interface IFormData {
  content: string;
}

interface Props {
  closeDialog: any;
}

export default function RequestHostAuth(props: Props) {
  const { enqueueSnackbar } = useSnackbar();
  const [user, setUser] = useRecoilState(userState);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<IFormData>();
  const onValid = async (formData: IFormData) => {
    if (!user) return;
    try {
      await requestHostAuth(user.id, formData.content);
      const userInfo = await getUserInfoByUserId(user.id);
      setUser(userInfo);
    } catch (error) {
      enqueueSnackbar('문제가 발생했습니다. 다시 시도하세요.', { variant: 'error' });
      return;
    }
    enqueueSnackbar('요청이 전송되었습니다. 응답을 기다려주세요.', { variant: 'success' });
    props.closeDialog();
  };

  return (
    <Card sx={{ p: 3, width: 400 }} component="form" onSubmit={handleSubmit(onValid)}>
      <InputLabel sx={{ mb: 1, color: 'text.primary' }}>신청 사유</InputLabel>
      <TextField
        {...register('content', { required: '필수 입력 항목입니다' })}
        size="small"
        placeholder="신청 사유를 작성해주세요."
        helperText={errors.content?.message}
        error={Boolean(errors.content?.message)}
        rows={3}
        multiline
        fullWidth
      />
      <Box sx={{ height: 24 }} />
      <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
        <Button variant="contained" type="submit">
          주최 권한 신청하기
        </Button>
      </Box>
    </Card>
  );
}
