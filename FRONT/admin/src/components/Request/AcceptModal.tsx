import { Box, InputLabel, Switch, TextField } from '@mui/material';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { useSnackbar } from 'notistack';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { acceptRequest } from '../../apis/request';

interface IFormData {
  duration: string;
}

interface IProps {
  id: number;
  affiliation:string;
  loadData: () => Promise<void>;
}

export default function AcceptModal({ id, loadData, affiliation}: IProps) {
  const { enqueueSnackbar } = useSnackbar();
  const [open, setOpen] = useState(false);
  const handleClickOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  const [hasDuration, setHasDuration] = useState(false);
  const handlChangeHasDuration = (event: React.ChangeEvent<HTMLInputElement>) => {
    setHasDuration(event.target.checked);
  };
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<IFormData>({ defaultValues: { duration: '' } });
  const onValid = async (formData: IFormData) => {
    try {
      await acceptRequest(id, hasDuration ? formData.duration : '', affiliation);
      await loadData();
    } catch (error) {
      enqueueSnackbar('문제가 발생했습니다. 다시 시도하세요.', { variant: 'error' });
      return;
    }
    enqueueSnackbar('수락 처리되었습니다.', { variant: 'success' });
    handleClose();
  };

  return (
    <div>
      <Button variant="contained" color="success" onClick={handleClickOpen}>
        수락
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <form onSubmit={handleSubmit(onValid)}>
          <DialogTitle>권한 요청을 수락하시겠습니까?</DialogTitle>
          <DialogContent sx={{ width: 400, maxWidth: 1 }}>
            <InputLabel sx={{ mb: 1, color: 'text.primary' }}>
              권한 마감 기한을 설정하시겠습니까?
            </InputLabel>
            <Switch checked={hasDuration} onChange={handlChangeHasDuration} />
            <Box sx={{ height: 16 }} />
            <InputLabel sx={{ mb: 1, color: 'text.primary' }}>권한 마감 기한</InputLabel>
            <TextField
              {...register('duration', {
                required: { value: hasDuration, message: '권한 마감 기한을 입력하세요.' },
              })}
              size="small"
              placeholder="권한 마감 기한을 입력하세요."
              helperText={hasDuration && errors.duration?.message}
              error={hasDuration && Boolean(errors.duration?.message)}
              fullWidth
              type="date"
              disabled={!hasDuration}
            />
            <Box sx={{ height: 16 }} />
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose}>취소</Button>
            <Button type="submit" variant="contained" color="success" autoFocus>
              수락
            </Button>
          </DialogActions>
        </form>
      </Dialog>
    </div>
  );
}
