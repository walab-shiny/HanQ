import { useState } from 'react';
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  InputLabel,
  TextField,
} from '@mui/material';
import { useSnackbar } from 'notistack';
import PlaylistAddIcon from '@mui/icons-material/PlaylistAdd';

interface IAddTagDialogProps {
  handleAddTag: (name: string) => void;
}

export default function AddTagDialog({ handleAddTag }: IAddTagDialogProps) {
  const { enqueueSnackbar } = useSnackbar();
  const [open, setOpen] = useState(false);
  const [tagName, setTagName] = useState('');

  const handleClickOpen = () => setOpen(true);

  const handleClose = () => setOpen(false);

  const handleAdd = () => {
    handleAddTag(tagName);
    setTagName('');
    enqueueSnackbar('태그가 추가되었습니다.', { variant: 'success' });
    handleClose();
  };

  return (
    <>
      <Button
        variant="contained"
        onClick={handleClickOpen}
        endIcon={<PlaylistAddIcon />}
        size="small"
      >
        태그 추가하기
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>태그 추가하기</DialogTitle>
        <DialogContent sx={{ width: 400, height: 80 }}>
          <InputLabel>태그 이름</InputLabel>
          <TextField
            size="small"
            value={tagName}
            onChange={(e) => setTagName(e.target.value)}
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="error" variant="outlined">
            취소
          </Button>
          <Button onClick={handleAdd} color="success" variant="contained" autoFocus>
            추가
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
