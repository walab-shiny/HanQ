import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import HomeIcon from '@mui/icons-material/Home';
import MenuBookIcon from '@mui/icons-material/MenuBook';
import ListAltIcon from '@mui/icons-material/ListAlt';
import PersonIcon from '@mui/icons-material/Person';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

interface propsType {
  page: number;
}

export default function MenuList(props: propsType) {
  const [selectedIndex, setSelectedIndex] = useState(props.page);

  const handleListItemClick = (
    event: React.MouseEvent<HTMLDivElement, MouseEvent>,
    index: number,
  ) => {
    setSelectedIndex(index);
  };

  const navigate = useNavigate();

  return (
    <Box
      sx={{
        width: '100%',
        bgcolor: 'background.paper',
        flexGrow: 1,
      }}
    >
      <List component="nav" aria-label="main mailbox folders">
        <ListItemButton
          selected={selectedIndex === 0}
          onClick={(event) => {
            handleListItemClick(event, 0);
            navigate('/');
          }}
          sx={{ padding: 1.5, margin: 1, borderRadius: 1 }}
        >
          <ListItemIcon>
            <HomeIcon />
          </ListItemIcon>
          <ListItemText primary="대시보드" />
        </ListItemButton>

        <ListItemButton
          selected={selectedIndex === 1}
          onClick={(event) => {
            handleListItemClick(event, 1);
            navigate('/event');
          }}
          sx={{ padding: 1.5, margin: 1, borderRadius: 1 }}
        >
          <ListItemIcon>
            <ListAltIcon />
          </ListItemIcon>
          <ListItemText primary="행사 목록 관리" />
        </ListItemButton>
        <ListItemButton
          selected={selectedIndex === 2}
          onClick={(event) => {
            handleListItemClick(event, 1);
            // navigate('/event');
          }}
          sx={{ padding: 1.5, margin: 1, borderRadius: 1 }}
        >
          <ListItemIcon>
            <MenuBookIcon />
          </ListItemIcon>
          <ListItemText primary="소감문 목록 관리" />
        </ListItemButton>
        <ListItemButton
          selected={selectedIndex === 3}
          onClick={(event) => {
            handleListItemClick(event, 1);
            // navigate('/event');
          }}
          sx={{ padding: 1.5, margin: 1, borderRadius: 1 }}
        >
          <ListItemIcon>
            <PersonIcon />
          </ListItemIcon>
          <ListItemText primary="프로필 수정" />
        </ListItemButton>
      </List>
    </Box>
  );
}
