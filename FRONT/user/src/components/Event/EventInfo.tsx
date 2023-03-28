import {
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
} from '@mui/material';
import { useState } from 'react';
import ParticipantDialog from './ParticipantDialog';
import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
import { useNavigate } from 'react-router-dom';
import { ReactMarkdown } from 'react-markdown/lib/react-markdown';

interface IEvent {
  category: string;
  name: string;
  location: string;
  content: string;
  open_at: Date;
  max_users: number;
  late_time: Date;
  image: string;
  cnt: number;
}

export default function EventInfo() {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const navigate = useNavigate();

  const content = `과학기술정보통신부와 정보통신기획평가원에서는 2023년도 하반기 ICT 글로벌 인턴십 과정 운영 계획을 다음과 같이 공고하오니, 참여를 희망하는 학생들은 아래의 내용을 참고하여 주시기 바랍니다.

  1. 사업개요
  
     정보통신 관련학과 대학생이 학점 인정을 조건으로 국내.외 기업에서 제안한 ICT관련 직무 중심 인턴십 수행
  
  2. 인턴십 시간 : 6개월 (7~12월)
  
  3. 파견지역 : 미국 실리콘 밸리 *참여기업 현황은 추천 대상자에 한해 4월 중순 개별 안내 예정
  
  4. 지원내용
  
    – 체재 준비금 (정부지원) : 왕복항공, 의료보험 및 비자발급 수수료등
  
    – 실습생 수당 (기업부담) : 월 $2,000이상
  
    – 실습생 지원금 (정부지원) : 최대 월 150만원
  
  5. 모집대상 : 정보통신 관련학과 재학중인 전공자(복수전공, 부전공, 복수전공 재학생 포함)
  
     – 전체 교육과정의 75% 이상 이수한 자(4년제 기준 6학기, 3년제 기준 5학기, 2년제 기준 3학기 이상)
  – 참여대학 내 참여학과 전공/ 부전공/ 복수전공 재학생
  – 7월 ~ 12월 인턴십 기간동안 정상적으로 학점 이수 가능한 재학생 신분을 유지하여야 함
  – 졸업유예자, 초과학기생의 경우 운영기관에서 참여를 제재하는 사항은 없으나 대학별 교내 규정, 지침 등에 의해 참여`;

  return (
    <Box
      sx={{
        // width: '100%',
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
            [4단계 BK21 AI사업단] 인공지능 워크샵 개최
          </Typography>
        </Box>
        <Button size="small" variant="contained" color="secondary" onClick={handleOpen}>
          명단 보기
        </Button>
        <ParticipantDialog open={open} onClose={handleClose} />
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
        <Typography>전산전자공학부</Typography>
        <Typography variant="subtitle1">장소</Typography>
        <Typography>NTH 311</Typography>
        <Typography variant="subtitle1">개최일</Typography>
        <Typography>2023-03-29</Typography>
        <Typography variant="subtitle1">최대 인원수</Typography>
        <Typography>30</Typography>
        <Typography variant="subtitle1">수강인원</Typography>
        <Typography>25</Typography>
      </Box>
      <ReactMarkdown children={content} />
      <Typography variant="subtitle1">사진</Typography>
    </Box>
  );
}
