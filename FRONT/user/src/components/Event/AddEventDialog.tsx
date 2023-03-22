import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  MenuItem,
  Select,
  SelectChangeEvent,
  TextField,
} from '@mui/material';
import { ChangeEvent, useState } from 'react';

interface Props {
  open: boolean;
  onClose: (value: void) => void;
}

export default function AddEventDialog(props: Props) {
  const { onClose, open } = props;

  const handleClose = () => {
    onClose();
  };

  const [name, setName] = useState('');
  const [category, setCategory] = useState('1');
  const [location, setLocation] = useState('');

  const changeName = (event: ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value as string);
  };
  const changeCate = (event: SelectChangeEvent) => {
    setCategory(event.target.value as string);
  };
  const changeLoca = (event: ChangeEvent<HTMLInputElement>) => {
    setLocation(event.target.value as string);
  };

  return (
    <Dialog onClose={handleClose} open={open} fullWidth>
      <DialogTitle>행사 등록</DialogTitle>
      <DialogContent>
        <DialogContentText pb={1}>카테고리</DialogContentText>
        <Select value={category} onChange={changeCate} size="small" sx={{ mb: 3 }}>
          <MenuItem value={'1'}>전산전자공학부</MenuItem>
          <MenuItem value={'2'}>콘텐츠융합디자인학부</MenuItem>
          <MenuItem value={'3'}>생명과학부</MenuItem>
          <MenuItem value={'4'}>공간환경시스템공학부</MenuItem>
          <MenuItem value={'5'}>기계제어공학부</MenuItem>
          <MenuItem value={'6'}>법학부</MenuItem>
          <MenuItem value={'7'}>ICT창업학부</MenuItem>
          <MenuItem value={'8'}>커뮤니케이션학부</MenuItem>
        </Select>
        <DialogContentText pb={1}>이름</DialogContentText>
        <TextField
          autoFocus
          id="name"
          label="행사 이름을 입력하세요."
          value={name}
          fullWidth
          hiddenLabel
          size="small"
          onChange={changeName}
          sx={{ mb: 3 }}
        />
        <DialogContentText pb={1}>장소</DialogContentText>
        <TextField
          autoFocus
          id="location"
          label="행사 장소를 입력하세요."
          fullWidth
          hiddenLabel
          size="small"
          onChange={changeLoca}
          sx={{ mb: 3 }}
        />
        <DialogContentText pb={1}>설명</DialogContentText>
        <TextField
          autoFocus
          id="location"
          label="행사 설명을 입력하세요."
          fullWidth
          hiddenLabel
          size="small"
          multiline
          rows={4}
          onChange={changeLoca}
          sx={{ mb: 3 }}
        />
        <DialogContentText pb={1}>개최일</DialogContentText>
        <TextField fullWidth hiddenLabel size="small" type="date" sx={{ mb: 3 }} />
        <DialogContentText pb={1}>최대 인원수</DialogContentText>
        <TextField
          autoFocus
          id="location"
          label="최대 인원수를 입력하세요."
          fullWidth
          hiddenLabel
          size="small"
          sx={{ mb: 3 }}
        />
        <DialogContentText pb={1}>지각허용시간</DialogContentText>
        <TextField fullWidth hiddenLabel size="small" type="time" sx={{ mb: 3 }} />
        <DialogContentText pb={1}>사진</DialogContentText>
        <input type="file" accept="image/x-png, image/gif, image/jpeg" />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} variant="outlined">
          취소
        </Button>
        <Button onClick={handleClose} variant="contained" sx={{ fontWeight: 'bold' }}>
          등록
        </Button>
      </DialogActions>
    </Dialog>
  );
}
