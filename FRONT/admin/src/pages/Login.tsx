import { Avatar, Button, TextField, Box, Typography, Container } from '@mui/material';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
// import { login } from './services/auth';
import { useForm } from 'react-hook-form';
import { CenterBox } from '../components/Header/common/CenterBox';
import { useSetRecoilState } from 'recoil';
import { authState } from '../store/auth';
import { userState } from '../store/user';

function Login() {
  const setCredential = useSetRecoilState(authState);
  const setUser = useSetRecoilState(userState);
  const saveCredential = (credential: string) => {
    localStorage.setItem('credential', credential);
    setCredential(credential);
  };
  const handleLogin = async () => {
    saveCredential('credential');
    setUser(true);
  };
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<{ id: string; password: string }>();
  const onValid = ({ id, password }: { id: string; password: string }) => {
    try {
      // login(id, password);
      handleLogin();
    } catch (error) {
      alert('Login failed.');
    }
  };

  return (
    <CenterBox>
      <Container maxWidth="xs">
        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <Avatar sx={{ m: 1, bgcolor: 'primary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            HanQ 관리자 서비스
          </Typography>
        </Box>
        <Box component="form" onSubmit={handleSubmit(onValid)} sx={{ mt: 1 }}>
          <TextField
            {...register('id', { required: '필수 입력 항목입니다' })}
            placeholder="아이디를 입력해주세요"
            helperText={errors.id?.message}
            error={Boolean(errors.id?.message)}
            fullWidth
            margin="normal"
            label="ID"
          />
          <TextField
            {...register('password', { required: '필수 입력 항목입니다' })}
            placeholder="비밀번호를 입력해주세요"
            helperText={errors.password?.message}
            error={Boolean(errors.password?.message)}
            fullWidth
            margin="normal"
            label="Password"
            type="password"
          />
          <Button type="submit" fullWidth variant="contained" size="large" sx={{ mt: 3 }}>
            Sign In
          </Button>
        </Box>
      </Container>
    </CenterBox>
  );
}

export default Login;
