import Box from "@mui/material/Box";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Card from "@mui/material/Card";

export default function CardList() {
  const notis = [
    "3일 뒤 ‘ChatGPT 강연’ 행사가 예정되어 있습니다.",
    "이번 달에는 4개의 행사가 예정되어 있습니다.",
    "행사 관리 페이지 바로가기 =>",
  ];
  return (
    <Box width="50%">
      {notis.map((noti, idx) => (
        <Box width="100%" m={3}>
          <Card>
            <CardContent>
              <Typography variant="h5" component="div">
                {noti}
              </Typography>
            </CardContent>
            {/* <CardActions>
            <Button size="small">Learn More</Button>
          </CardActions> */}
          </Card>
        </Box>
      ))}
    </Box>
  );
}
