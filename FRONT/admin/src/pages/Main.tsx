import { Avatar, Box, Card, Chip, Container, Divider, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import { getAllRequests, getPendingRequests } from '../apis/request';
import LogoutBtn from '../components/LogoutBtn';
import AcceptModal from '../components/Request/AcceptModal';
import DeclineModal from '../components/Request/DeclineModal';
import { addTag, deleteTag, getTags, updateTag } from '../apis/tags';
import AddTagDialog from '../components/Tag/AddTagDialog';
import UserInfoModal from '../components/Request/UserInfoModal';

interface ITag {
  id: number;
  name: string;
}

export interface IUser {
  affiliation: string;
  department: string;
  email: string;
  hostUntil: string;
  id: number;
  isHost: true;
  isPending: false;
  isRegistered: true;
  isStudent: false;
  name: string;
  picture: string;
  requestDate: string;
  studentNum: number;
  token: string;
}
interface IRequest {
  userId: number;
  id: number;
  status: number;
  content: string;
  response: string;
  createdAt: string;
  modifiedAt: string;
  user: IUser;
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
  const getDisplayTime = (time: string) => {
    return time.split('T')[0] + ' ' + time.split('T')[1];
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
                <Box>
                  <Box sx={{ display: 'flex', alignItems: 'center', gap: 2, mb: 2 }}>
                    <Avatar src={request.user.picture} />
                    <Typography variant="h6">{request.user.name}</Typography>
                    <Box sx={{ flexGrow: 1 }} />
                    <UserInfoModal user={request.user} />
                  </Box>
                  <Typography gutterBottom>
                    <span style={{ fontWeight: 600 }}>권한 요청 사유:</span> {request.content}
                  </Typography>
                  <Typography>
                    <span style={{ fontWeight: 600 }}>신청일:</span>{' '}
                    {getDisplayTime(request.createdAt)}
                  </Typography>
                </Box>
                <Box sx={{ flexGrow: 1 }} />
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
            {requests
              .sort((a, b) => {
                // 최신 순으로 정렬
                if (a.modifiedAt > b.modifiedAt) {
                  return -1;
                }
                if (a.modifiedAt < b.modifiedAt) {
                  return 1;
                }
                return 0;
              })
              .map((request) => (
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
                  <Box>
                    <Box sx={{ display: 'flex', alignItems: 'center', gap: 2, mb: 2 }}>
                      <Avatar src={request.user.picture} />
                      <Typography variant="h6">{request.user.name}</Typography>
                      <Box sx={{ flexGrow: 1 }} />
                      <UserInfoModal user={request.user} />
                    </Box>
                    <Typography gutterBottom>
                      <span style={{ fontWeight: 600 }}>권한 요청 사유:</span> {request.content}
                    </Typography>
                    <Typography>
                      <span style={{ fontWeight: 600 }}>신청일:</span>{' '}
                      {getDisplayTime(request.createdAt)}
                    </Typography>
                    {request.status !== 1 && (
                      <Typography>
                        <span style={{ fontWeight: 600 }}>응답일:</span>{' '}
                        {getDisplayTime(request.modifiedAt)}
                      </Typography>
                    )}
                  </Box>
                  <Box sx={{ flexGrow: 1 }} />
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
