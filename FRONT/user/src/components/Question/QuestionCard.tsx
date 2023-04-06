import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';

export default function QuestionCard() {
  return (
    <Card sx={{ width: 'calc(45vw)', mb: 2 }}>
      <CardHeader
        avatar={<Avatar aria-label="recipe">김</Avatar>}
        action={
          <>
            <IconButton>
              <FavoriteIcon />
            </IconButton>
          </>
        }
        title="김한동"
        titleTypographyProps={{ fontWeight: 'bold' }}
        subheader="September 14, 2016"
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          This impressive paella is a perfect party dish and a fun meal to cook together with your
          guests. Add 1 cup of frozen peas along with the mussels, if you like.
        </Typography>
      </CardContent>
    </Card>
  );
}
