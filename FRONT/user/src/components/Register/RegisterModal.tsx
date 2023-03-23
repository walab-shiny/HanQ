import {
  Box,
  Button,
  Dialog,
  FormControl,
  FormHelperText,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from '@mui/material';
import { Controller, useForm } from 'react-hook-form';
import LogoutBtn from '../LogoutBtn';
import { otherRegister, studentRegister } from '../../apis/auth';
import { useRecoilState } from 'recoil';
import { userState } from '../../store/user';
import { CenterBox } from '../StyledComponents/CenterBox';

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
      if (user.isStudent) {
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
    <Dialog open={open} fullScreen>
      <CenterBox>
        {user && (
          <Box component="form" onSubmit={handleSubmit(onValid)}>
            <Typography variant="h4">회원가입</Typography>
            <Box sx={{ height: 32 }} />
            <Box sx={{ width: 300, maxHeight: 1 }}>
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
            </Box>
            <Box sx={{ height: 32 }} />
            <Box sx={{ display: 'flex', justifyContent: 'flex-end', gap: 2 }}>
              <LogoutBtn>돌아가기</LogoutBtn>
              <Button variant="contained" color="success" type="submit">
                가입하기
              </Button>
            </Box>
          </Box>
        )}
      </CenterBox>
    </Dialog>
  );
}
