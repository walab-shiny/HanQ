import {
  Autocomplete,
  Avatar,
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  IconButton,
  TextField,
} from '@mui/material';
import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { FileUploader } from 'react-drag-drop-files';
import { IEvent } from '../../types/event';
import { addEvent, addEventWithFile } from '../../apis/event';
import AddIcon from '@mui/icons-material/Add';
import { getTagList } from '../../apis/tags';
import { ITag } from '../../types/tag';

interface Props {
  fetchData: () => void;
}

const fileTypes = ['JPEG', 'PNG', 'GIF', 'JPG'];

export default function AddEventDialog({ fetchData }: Props) {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [selectedImage, setSelectedImage] = useState<File | null>(null);
  const handleChange = (file: File) => {
    setSelectedImage(file);
  };
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
    setValue,
  } = useForm<IEvent>({ defaultValues: { tags: [] } });

  const onValid = async (data: IEvent) => {
    if (selectedImage) {
      await addEventWithFile(data, selectedImage);
    } else {
      await addEvent(data);
    }
    fetchData();
    reset();
    handleClose();
  };

  const [tags, setTags] = useState<ITag[]>([]);
  useEffect(() => {
    const getTags = async () => {
      const { data } = await getTagList();
      setTags(data);
    };
    getTags();
  }, []);

  return (
    <>
      <IconButton onClick={handleOpen}>
        <AddIcon />
      </IconButton>
      <Dialog onClose={handleClose} open={open} fullWidth>
        <Box component="form" onSubmit={handleSubmit(onValid)}>
          <Box display="flex" alignItems="center" justifyContent="space-between" mr={3}>
            <DialogTitle>이벤트 등록</DialogTitle>
            <Box>
              <FileUploader handleChange={handleChange} name="file" types={fileTypes} />
              {selectedImage && (
                <Avatar
                  alt="event image"
                  src={URL.createObjectURL(selectedImage)}
                  sx={{ width: 256, height: 256 }}
                  variant="rounded"
                />
              )}
            </Box>
          </Box>
          <DialogContent>
            <DialogContentText pb={1}>태그</DialogContentText>
            <Autocomplete
              onChange={(event: any, newValue: ITag[]) => {
                setValue('tags', newValue);
              }}
              multiple
              options={tags}
              getOptionLabel={(option) => option.name}
              renderInput={(params) => (
                <TextField {...params} size="small" placeholder="관련 태그를 입력하세요." />
              )}
              sx={{ mb: 3 }}
            />
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
              onChange={(e) => setValue('closeAt', e.target.value)}
              // onChange={(e) => setValue('endTime', e.target.value)}
              helperText={errors.openAt?.message}
              error={Boolean(errors.openAt?.message)}
            />
            <DialogContentText pb={1}>종료일시</DialogContentText>
            <TextField
              fullWidth
              size="small"
              type="datetime-local"
              sx={{ mb: 3 }}
              {...register('closeAt', { required: '종료일시는 필수 입력 항목입니다.' })}
              helperText={errors.closeAt?.message}
              error={Boolean(errors.closeAt?.message)}
            />
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
            <Button onClick={handleClose} variant="outlined" color="error">
              취소
            </Button>
            <Button type="submit" variant="contained" color="success">
              등록
            </Button>
          </DialogActions>
        </Box>
      </Dialog>
    </>
  );
}
