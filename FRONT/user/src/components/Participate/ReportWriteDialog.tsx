import {
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TextField,
} from '@mui/material';
import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';

interface Props {
  open: boolean;
  onClose: (value: void) => void;
}

interface IReport {
  content: string;
}

export default function ReportWriteDialog(props: Props) {
  const { onClose, open } = props;
  const [isSubmitted, setIsSubmitted] = useState(false);

  const {
    watch,
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<IReport>();

  const handleClose = () => {
    onClose();
  };

  const onValid = (data: any) => {
    console.log(data);
    setIsSubmitted(true);
    reset();
    onClose();
  };

  useEffect(() => {
    reset({ content: '' });
  }, [isSubmitted]);

  return (
    <>
      <Dialog open={open} onClose={handleClose} fullWidth>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <DialogTitle>소감문 작성</DialogTitle>
        </Box>
        <DialogContent>
          <TextField
            placeholder="50자 이상의 소감문을 작성하세요."
            rows={5}
            multiline
            fullWidth
            autoFocus
            {...register('content', {
              required: '소감문은 필수 입력 항목입니다.',
              minLength: { value: 50, message: '50자 이상 작성해야 합니다.' },
            })}
            helperText={errors.content?.message ?? watch('content')?.length + '/50'}
            error={Boolean(errors.content?.message)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} variant="outlined">
            취소
          </Button>
          <Button onClick={handleSubmit(onValid)} variant="contained" type="submit">
            제출
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
