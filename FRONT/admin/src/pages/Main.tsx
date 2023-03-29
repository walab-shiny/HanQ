import { Box, Button, Card, Chip, Container, Divider, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import { getAllRequests, getPendingRequests } from '../apis/request';
import AcceptModal from '../components/Request/AcceptModal';
import DeclineModal from '../components/Request/DeclineModal';

interface IRequest {
  userId: number;
  id: number;
  status: number;
  content: string;
  response: string;
}

export default function Main() {
  const [requests, setRequests] = useState<IRequest[]>([]);
  const [pendingRequests, setPendingRequests] = useState<IRequest[]>([]);

  const loadData = async () => {
    const allRequests = await getAllRequests();
    const pendingRequests = await getPendingRequests();
    console.log(allRequests);
    setRequests(allRequests);
    setPendingRequests(pendingRequests);
  };

  useEffect(() => {
    loadData();
  }, []);

  return (
    <Container>
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          py: 4,
        }}
      >
        <Typography variant="h3">행사 주최 권한 요청 관리</Typography>
        <Box>
          <Button variant="contained" onClick={() => {}}>
            로그아웃
          </Button>
        </Box>
      </Box>
      <Box sx={{ display: 'flex', gap: 4, pb: 8 }}>
        <Box sx={{ height: 16 }} />
        <Box sx={{ width: 1 }}>
          <Typography variant="h5">대기중인 요청</Typography>
          <Box
            sx={{
              display: 'grid',
              gridTemplateColumns: {
                lg: `repeat(2, 1fr)`,
              },
              gap: 3,
            }}
          >
            {pendingRequests.map((request) => (
              <Card
                key={request.id}
                sx={{
                  p: 2,
                  display: 'flex',
                  flexDirection: 'column',
                  justifyContent: 'center',
                  gap: 2,
                }}
              >
                <Typography variant="subtitle1">유저 ID: {request.userId}</Typography>
                <Typography>권한 요청 사유: {request.content}</Typography>
                <Box sx={{ height: 16 }} />
                <Box
                  sx={{ width: 1, display: 'flex', justifyContent: 'flex-end', mt: 'auto', gap: 2 }}
                >
                  <AcceptModal id={request.id} loadData={loadData} />
                  <DeclineModal id={request.id} loadData={loadData} />
                </Box>
              </Card>
            ))}
          </Box>
        </Box>
        <Divider orientation="vertical" flexItem />
        <Box sx={{ width: 1 }}>
          <Typography variant="h5">요청 기록</Typography>
          <Box sx={{ height: 16 }} />
          <Box
            sx={{
              display: 'grid',
              gridTemplateColumns: {
                lg: `repeat(2, 1fr)`,
              },
              gap: 3,
              pb: 8,
            }}
          >
            {requests.map((request) => (
              <Card
                key={request.id}
                sx={{
                  p: 2,
                  display: 'flex',
                  flexDirection: 'column',
                  justifyContent: 'center',
                  gap: 2,
                }}
              >
                <Typography variant="subtitle1">유저 ID: {request.userId}</Typography>
                <Typography>권한 요청 사유: {request.content}</Typography>
                <Box sx={{ height: 16 }} />
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                  {request.status === 0 && <Chip label="거절됨" color="error" />}
                  {request.status === 1 && <Chip label="대기중" />}
                  {request.status === 2 && <Chip label="수락됨" color="success" />}
                  {request.status === 0 && request.response !== '' && (
                    <Typography>사유: {request.content}</Typography>
                  )}
                </Box>
              </Card>
            ))}
          </Box>
        </Box>
      </Box>
    </Container>
  );
}
