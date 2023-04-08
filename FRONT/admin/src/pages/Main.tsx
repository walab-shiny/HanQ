import { Box, Card, Chip, Container, Divider, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import { getAllRequests, getPendingRequests } from '../apis/request';
import LogoutBtn from '../components/LogoutBtn';
import AcceptModal from '../components/Request/AcceptModal';
import DeclineModal from '../components/Request/DeclineModal';
import { addTag, deleteTag, getTags, updateTag } from '../apis/tags';
import AddTagDialog from '../components/Tag/AddTagDialog';

interface ITag {
  id: number;
  name: string;
}
interface IRequest {
  userId: number;
  id: number;
  status: number;
  content: string;
  response: string;
}

export default function Main() {
  const [requests, setRequests] = useState<IRequest[]>([]);
  const [tags, setTags] = useState<ITag[]>([]);
  const [pendingRequests, setPendingRequests] = useState<IRequest[]>([]);

  const fetchTags = async () => {
    const tags = await getTags();
    setTags(tags);
  };

  const handleAddTag = async (name: string) => {
    await addTag(name);
    fetchTags();
  };

  const handledeleteTag = async (id: number) => {
    if (window.confirm('정말 삭제하시겠습니까?')) {
      await deleteTag(id);
      fetchTags();
    }
  };

  const handleUpdateTag = async (id: number, name: string) => {
    const newName = window.prompt('태그 이름을 입력하세요', name);
    if (!newName) return;
    await updateTag(id, newName);
    fetchTags();
  };

  const showTags = () => {
    return tags.map((tag) => (
      <Chip
        key={tag.id}
        label={tag.name}
        sx={{ mr: 1, mb: 1 }}
        onClick={() => handleUpdateTag(tag.id, tag.name)}
        onDelete={() => handledeleteTag(tag.id)}
      />
    ));
  };

  const loadData = async () => {
    const allRequests = await getAllRequests();
    const pendingRequests = await getPendingRequests();
    setRequests(allRequests);
    setPendingRequests(pendingRequests);
  };

  useEffect(() => {
    fetchTags();
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
          <LogoutBtn>로그아웃</LogoutBtn>
        </Box>
      </Box>
      <Box sx={{ minHeight: 300 }}>
        <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 2 }}>
          <Typography variant="h5">태그 목록</Typography>
          <AddTagDialog handleAddTag={handleAddTag} />
        </Box>
        {showTags()}
      </Box>
      <Box sx={{ display: 'flex', gap: 4, pb: 8 }}>
        <Box sx={{ width: 1 }}>
          <Typography variant="h5">대기중인 요청</Typography>
          <Box sx={{ height: 16 }} />
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
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                  {request.status === 0 && <Chip label="거절됨" color="error" />}
                  {request.status === 1 && <Chip label="대기중" />}
                  {request.status === 2 && <Chip label="수락됨" color="success" />}
                  {request.status === 0 && request.response !== '' && (
                    <Typography>사유: {request.response}</Typography>
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
