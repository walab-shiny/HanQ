import { InputLabel, TextField } from '@mui/material';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { useSnackbar } from 'notistack';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { declineRequest } from '../../apis/request';

interface IFormData {
  message: string;
}

interface IProps {
  id: number;
  loadData: () => Promise<void>;
}

export default function DeclineModal({ id, loadData }: IProps) {
  const { enqueueSnackbar } = useSnackbar();
  const [open, setOpen] = useState(false);
  const handleClickOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<IFormData>();
  const onValid = async (formData: IFormData) => {
    try {
      await declineRequest(id, formData.message);
      await loadData();
    } catch (error) {
      enqueueSnackbar('문제가 발생했습니다. 다시 시도하세요.', { variant: 'error' });
      return;
    }
    enqueueSnackbar('거절 처리되었습니다.', { variant: 'success' });
    handleClose();
  };

  return (
    <div>
      <Button variant="contained" color="error" onClick={handleClickOpen}>
        거절
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <form onSubmit={handleSubmit(onValid)}>
          <DialogTitle>권한 요청을 거절하시겠습니까?</DialogTitle>
          <DialogContent sx={{ width: 400, maxWidth: 1 }}>
            <InputLabel sx={{ mb: 1, color: 'text.primary' }}>거절 사유</InputLabel>
            <TextField
              {...register('message', { required: '필수 입력 항목입니다' })}
              size="small"
              placeholder="거절 사유를 작성해주세요."
              helperText={errors.message?.message}
              error={Boolean(errors.message?.message)}
              rows={3}
              multiline
              fullWidth
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose}>취소</Button>
            <Button type="submit" variant="contained" color="error" autoFocus>
              거절
            </Button>
          </DialogActions>
        </form>
      </Dialog>
    </div>
  );
}
