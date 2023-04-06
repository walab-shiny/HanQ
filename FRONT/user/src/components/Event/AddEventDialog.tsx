import {
  Box,
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
import { FileUploader } from 'react-drag-drop-files';
import { IEvent } from '../../types/event';
import { addEvent } from '../../apis/event';
import TagSelect from '../Setting/TagSelect';

interface Props {
  open: boolean;
  onClose: (value: void) => void;
  fetchData: () => void;
}

const fileTypes = ['JPEG', 'PNG', 'GIF', 'JPG'];

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
  } = useForm<IEvent>({ mode: 'onChange' });

  const onValid = async (data: IEvent) => {
    await addEvent(data);
    props.fetchData();
    console.log(data);
    handleClose();
  };

  useEffect(() => {
    reset({});
  }, [isSubmitted]);

  const [file, setFile] = useState(null);
  const handleChange = (file: any) => {
    console.log(file);
    setFile(file);
  };

  return (
    <Dialog onClose={handleClose} open={open} fullWidth>
      <Box display="flex" alignItems="center" justifyContent="space-between" mr={3}>
        <DialogTitle>이벤트 등록</DialogTitle>
        <FileUploader multiline={true} handleChange={handleChange} name="file" types={fileTypes} />
      </Box>
      <DialogContent>
        <DialogContentText pb={1}>태그</DialogContentText>
        {/* <Select size="small" sx={{ mb: 3 }} {...register('tags')} value={1}>
          <MenuItem value={1}>전산전자공학부</MenuItem>
          <MenuItem value={2}>콘텐츠융합디자인학부</MenuItem>
          <MenuItem value={3}>생명과학부</MenuItem>
          <MenuItem value={4}>공간환경시스템공학부</MenuItem>
          <MenuItem value={5}>기계제어공학부</MenuItem>
          <MenuItem value={6}>법학부</MenuItem>
          <MenuItem value={7}>ICT창업학부</MenuItem>
          <MenuItem value={8}>커뮤니케이션학부</MenuItem>
        </Select> */}
        <TagSelect />
        <Box sx={{ height: 24 }} />
        <DialogContentText pb={1}>이벤트 제목</DialogContentText>
        <TextField
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
          {...register('openAt', { required: '시작일시는 필수 입력 항목입니다.' })}
          // onChange={(e) => setValue('endTime', e.target.value)}
          helperText={errors.openAt?.message}
          error={Boolean(errors.openAt?.message)}
        />
        {/* <DialogContentText pb={1}>종료일시</DialogContentText>
        <TextField
          fullWidth
          size="small"
          type="datetime-local"
          sx={{ mb: 3 }}
          {...register('endTime', { required: '종료일시는 필수 입력 항목입니다.' })}
          helperText={errors.startTime?.message}
          error={Boolean(errors.startTime?.message)}
        /> */}
        {/* <DialogContentText pb={1}>태깅 가능 시간</DialogContentText>
        <TextField
          fullWidth
          size="small"
          sx={{ mb: 3 }}
          {...register('availiableTime')}
          placeholder="태깅 가능 시간(분)을 입력하세요."
        /> */}
        <DialogContentText pb={1}>최대 인원수</DialogContentText>
        <TextField
          id="maxCnt"
          placeholder="최대 인원수를 입력하세요."
          fullWidth
          size="small"
          sx={{ mb: 3 }}
          {...register('maxUsers')}
        />
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
