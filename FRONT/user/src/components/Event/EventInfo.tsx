import { Box, Button, Chip, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import ParticipantDialog from './ParticipantDialog';
import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
import { useNavigate, useParams } from 'react-router-dom';
import { ReactMarkdown } from 'react-markdown/lib/react-markdown';
import { IEvent } from '../../types/event';
import { getEvent } from '../../apis/event';

export default function EventInfo() {
  const { id } = useParams();
  const [open, setOpen] = useState(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const navigate = useNavigate();

  const [event, setEvent] = useState<IEvent>();

  const fetchData = async () => {
    const response = await getEvent(+id!);
    setEvent(response);
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
          <Button onClick={() => navigate('/event')} style={{ color: 'gray' }}>
            <KeyboardArrowLeftIcon />
          </Button>
          <Typography variant="h3" m={1} color="secondary">
            {event?.name}
          </Typography>
        </Box>
        <Button size="small" variant="contained" color="secondary" onClick={handleOpen}>
          명단 보기
        </Button>
        <ParticipantDialog open={open} onClose={handleClose} id={id!} />
      </Box>
      <Box
        bgcolor={'grey.200'}
        borderRadius={1}
        display="flex"
        justifyContent="space-evenly"
        p={1}
        m={1}
      >
        <Typography variant="subtitle1">카테고리</Typography>
        {(event?.tags ?? []).map((tag) => (
          <Chip key={tag.id} label={tag.name} sx={{ mr: 1 }} size="small" />
        ))}
        <Typography variant="subtitle1">장소</Typography>
        <Typography>{event?.location}</Typography>
        <Typography variant="subtitle1">개최일</Typography>
        <Typography>
          {event?.openAt?.split('T')[0]} {event?.openAt?.split('T')[1]}
        </Typography>
        <Typography variant="subtitle1">최대 인원수</Typography>
        <Typography>{event?.maxUsers}</Typography>
        <Typography variant="subtitle1">수강인원</Typography>
        <Typography>25</Typography>
      </Box>
      <Box height={'calc(63vh)'} overflow="scroll" textOverflow={'scroll'}>
        <ReactMarkdown>
          {event?.content === undefined ? '내용이 없습니다' : event?.content}
        </ReactMarkdown>
        <Typography variant="subtitle1">사진</Typography>
        <Box component="img" src={event?.image} sx={{ width: '100%' }} />
      </Box>
    </Box>
  );
}
