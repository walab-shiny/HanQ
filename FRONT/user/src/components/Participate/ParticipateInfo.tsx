import { Box, Button, Chip, Typography } from '@mui/material';
import { useEffect, useState } from 'react';

import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
import { useNavigate, useParams } from 'react-router-dom';
import { ReactMarkdown } from 'react-markdown/lib/react-markdown';
import ReportWriteDialog from './ReportWriteDialog';
import { IEvent } from '../../types/event';
import { getEvent } from '../../apis/event';

export default function ParticipageInfo() {
  const { id } = useParams();

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const navigate = useNavigate();

  const [event, setEvent] = useState<IEvent>();

  const fetchData = async () => {
    const response = await getEvent(+id!);
    setEvent(response);
    console.log(response);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <Box
      sx={{
        // maxWidth: '90%',
        bgcolor: 'background.paper',
        borderRadius: 3,
        p: 3,
      }}
    >
      <Box display="flex" justifyContent="space-between" alignItems="center">
        <Box gap={1} display="flex" alignItems="center">
          <Button onClick={() => navigate('/participate')} style={{ color: 'gray' }}>
            <KeyboardArrowLeftIcon />
          </Button>
          <Typography variant="h3" m={1} color="secondary">
            {event?.name}
          </Typography>
        </Box>
        <Button size="small" variant="contained" color="secondary" onClick={handleOpen}>
          소감문 작성
        </Button>
        <ReportWriteDialog open={open} onClose={handleClose} />
      </Box>
      <Box bgcolor={'grey.200'} borderRadius={1} display="flex" justifyContent="space-evenly" p={1}>
        <Box display="flex" gap={3}>
          <Typography variant="subtitle1">카테고리</Typography>
          {(event?.tags ?? []).map((tag) => (
            <Chip key={tag.id} label={tag.name} sx={{ mr: 1 }} size="small" />
          ))}
        </Box>
        <Box display="flex" gap={3}>
          <Typography variant="subtitle1">장소</Typography>
          <Typography>{event?.location}</Typography>
        </Box>
        <Box display="flex" gap={3}>
          <Typography variant="subtitle1">개최일</Typography>
          <Typography>
            {event?.openAt.split('T')[0]} {event?.openAt.split('T')[1]}
          </Typography>
        </Box>
      </Box>
      <Box height={'calc(63vh)'} overflow="scroll" textOverflow={'scroll'}>
        <ReactMarkdown children={event?.content ?? '내용이 없습니다.'} />
        <Box component="img" src={event?.image} sx={{ width: '100%' }} />
      </Box>
    </Box>
  );
}
