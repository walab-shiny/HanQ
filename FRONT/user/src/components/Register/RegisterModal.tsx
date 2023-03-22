import {
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  FormControl,
  FormHelperText,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from '@mui/material';
import { Controller, useForm } from 'react-hook-form';
import LogoutBtn from '../LogoutBtn';
import { otherRegister, studentRegister } from '../../apis/auth';
import { useRecoilState } from 'recoil';
import { userState } from '../../store/user';

const departmentList = [
  '전산전자공학부',
  '콘텐츠융합디자인학부',
  '생명과학부',
  '공간환경시스템공학부',
  '기계제어공학부',
  '법학부',
  '경영경제학부',
  'ICT창업학부',
  '커뮤니케이션학부',
];

interface IFormData {
  studentId: number;
  departmentId: number;
  affiliation: string;
}

interface Props {
  open: boolean;
}

export default function RegisterModal({ open }: Props) {
  const [user, setUser] = useRecoilState(userState);
  const {
    control,
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<IFormData>({ defaultValues: { departmentId: 1 } });
  const onValid = async (data: IFormData) => {
    if (user) {
      if (user.isRegistered) {
        const response = await studentRegister(user.id, +data.studentId, data.departmentId);
        setUser(response);
      } else {
        const response = await otherRegister(user.id, data.affiliation);
        setUser(response);
      }
      reset();
    } else {
      console.error('no user info');
    }
  };

  return (
    <Dialog open={open}>
      <DialogTitle variant="h4">회원가입</DialogTitle>
      <DialogContent sx={{ width: 300, maxHeight: 1 }}>
        {user?.isStudent ? (
          <Box>
            <InputLabel sx={{ color: 'text.primary' }}>학번</InputLabel>
            <TextField
              {...register('studentId', {
                required: '필수 입력 항목입니다',
                minLength: { value: 8, message: '학번 8글자를 입력해주세요.' },
                maxLength: { value: 8, message: '학번 8글자를 입력해주세요.' },
              })}
              size="small"
              placeholder="학번을 입력하세요."
              helperText={errors.studentId?.message}
              error={Boolean(errors.studentId?.message)}
              fullWidth
              type="number"
            />
            <Box sx={{ height: 16 }} />
            <InputLabel sx={{ color: 'text.primary' }}>학부</InputLabel>
            <FormControl fullWidth>
              <Controller
                name="departmentId"
                control={control}
                rules={{ required: '필수 입력 항목입니다.' }}
                render={({ field }) => (
                  <Select
                    {...field}
                    ref={register('departmentId').ref}
                    size="small"
                    error={Boolean(errors.departmentId?.message)}
                    displayEmpty
                  >
                    {departmentList.map((department, index) => (
                      <MenuItem key={index} value={index + 1}>
                        {department}
                      </MenuItem>
                    ))}
                  </Select>
                )}
              />
              {errors.departmentId && (
                <FormHelperText error={true}>{errors.departmentId.message}</FormHelperText>
              )}
            </FormControl>
          </Box>
        ) : (
          <Box>
            <InputLabel sx={{ color: 'text.primary' }}>소속</InputLabel>
            <TextField
              {...register('affiliation', {
                required: '필수 입력 항목입니다',
              })}
              size="small"
              placeholder="소속을 입력하세요."
              helperText={errors.affiliation?.message}
              error={Boolean(errors.affiliation?.message)}
              fullWidth
            />
          </Box>
        )}
      </DialogContent>
      <Box sx={{ height: 32 }} />
      <DialogActions>
        <LogoutBtn>돌아가기</LogoutBtn>
        <Button variant="contained" color="success" onClick={handleSubmit(onValid)}>
          가입하기
        </Button>
      </DialogActions>
    </Dialog>
  );
}
