import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  MenuItem,
  Select,
  TextField,
} from '@mui/material';
import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';

interface Props {
  open: boolean;
  onClose: (value: void) => void;
}

interface IForm {
  category: string;
  name: string;
  location: string;
  content: string;
  startTime: string;
  endTime: string;
  availiableTime: string;
  maxUsers: string;
}

export default function AddEventDialog(props: Props) {
  const { onClose, open } = props;
  const [isSubmitted, setIsSubmitted] = useState(false);

  const handleClose = () => {
    setIsSubmitted(true);
    onClose();
  };

  const {
    register,
    handleSubmit,
    reset,
    setValue,
    formState: { errors },
  } = useForm<IForm>({ mode: 'onChange' });

  const onValid = (data: any) => {
    console.log(data);
    handleClose();
  };

  useEffect(() => {
    reset({});
  }, [isSubmitted]);

  return (
    <Dialog onClose={handleClose} open={open} fullWidth>
      <DialogTitle>이벤트 등록</DialogTitle>
      <DialogContent>
        <DialogContentText pb={1}>태그</DialogContentText>
        <Select size="small" sx={{ mb: 3 }} {...register('category')} value={1}>
          <MenuItem value={'1'}>전산전자공학부</MenuItem>
          <MenuItem value={'2'}>콘텐츠융합디자인학부</MenuItem>
          <MenuItem value={'3'}>생명과학부</MenuItem>
          <MenuItem value={'4'}>공간환경시스템공학부</MenuItem>
          <MenuItem value={'5'}>기계제어공학부</MenuItem>
          <MenuItem value={'6'}>법학부</MenuItem>
          <MenuItem value={'7'}>ICT창업학부</MenuItem>
          <MenuItem value={'8'}>커뮤니케이션학부</MenuItem>
        </Select>
        <DialogContentText pb={1}>이벤트 제목</DialogContentText>
        <TextField
          autoFocus
          id="name"
          placeholder="이벤트명을 입력하세요."
          fullWidth
          hiddenLabel
          size="small"
          {...register('name', {
            required: '이벤트명은 필수 입력 항목입니다.',
          })}
          sx={{ mb: 3 }}
          helperText={errors.name?.message}
          error={Boolean(errors.name?.message)}
        />
        <DialogContentText pb={1}>장소</DialogContentText>
        <TextField
          autoFocus
          id="location"
          placeholder="이벤트 장소를 입력하세요."
          fullWidth
          hiddenLabel
          size="small"
          {...register('location', { required: '이벤트 장소는 필수 입력 항목입니다.' })}
          helperText={errors.location?.message}
          error={Boolean(errors.location?.message)}
          sx={{ mb: 3 }}
        />
        <DialogContentText pb={1}>설명</DialogContentText>
        <TextField
          autoFocus
          id="content"
          placeholder="이벤트 설명을 입력하세요."
          fullWidth
          size="small"
          multiline
          rows={4}
          {...register('content')}
          sx={{ mb: 3 }}
        />
        <DialogContentText pb={1}>시작일시</DialogContentText>
        <TextField
          fullWidth
          size="small"
          type="datetime-local"
          sx={{ mb: 3 }}
          {...register('startTime', { required: '시작일시는 필수 입력 항목입니다.' })}
          onChange={(e) => setValue('endTime', e.target.value)}
          helperText={errors.startTime?.message}
          error={Boolean(errors.startTime?.message)}
        />
        <DialogContentText pb={1}>종료일시</DialogContentText>
        <TextField
          fullWidth
          size="small"
          type="datetime-local"
          sx={{ mb: 3 }}
          {...register('endTime', { required: '종료일시는 필수 입력 항목입니다.' })}
          helperText={errors.startTime?.message}
          error={Boolean(errors.startTime?.message)}
        />
        <DialogContentText pb={1}>태깅 가능 시간</DialogContentText>
        <TextField
          fullWidth
          size="small"
          sx={{ mb: 3 }}
          {...register('availiableTime')}
          placeholder="태깅 가능 시간(분)을 입력하세요."
        />
        <DialogContentText pb={1}>최대 인원수</DialogContentText>
        <TextField
          autoFocus
          id="maxCnt"
          placeholder="최대 인원수를 입력하세요."
          fullWidth
          size="small"
          sx={{ mb: 3 }}
          {...register('maxUsers')}
        />
        <DialogContentText pb={1}>사진</DialogContentText>
        <input type="file" accept="image/x-png, image/gif, image/jpeg" />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} variant="outlined">
          취소
        </Button>
        <Button
          onClick={handleSubmit(onValid)}
          type="submit"
          variant="contained"
          sx={{ fontWeight: 'bold' }}
        >
          등록
        </Button>
      </DialogActions>
    </Dialog>
  );
}
