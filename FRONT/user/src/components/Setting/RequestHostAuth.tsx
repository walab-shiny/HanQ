import { Box, Button, Card, InputLabel, TextField } from '@mui/material';
import { useForm } from 'react-hook-form';
import { useRecoilValue } from 'recoil';
import { requestHostAuth } from '../../apis/request';
import { userState } from '../../store/user';

interface IFormData {
  content: string;
}

export default function RequestHostAuth() {
  const user = useRecoilValue(userState);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<IFormData>();
  const onValid = async (formData: IFormData) => {
    if (!user) return;
    await requestHostAuth(user.id, formData.content);
    console.log(formData);
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
