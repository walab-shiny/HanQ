import { Switch } from '@mui/material';
import { useRecoilState } from 'recoil';
import { themeModeState } from '../../store/atoms';

function ThemeModeToggle() {
  const [themeMode, setThemeMode] = useRecoilState(themeModeState);
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setThemeMode(event.target.checked ? 'dark' : 'light');
  };
  localStorage.setItem('themeMode', themeMode);
  return <Switch checked={themeMode === 'dark'} onChange={handleChange} />;
}

export default ThemeModeToggle;
