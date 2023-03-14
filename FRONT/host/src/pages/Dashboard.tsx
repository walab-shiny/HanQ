import { Box } from "@mui/material";
import Logo from "../components/Dashboard/Logo";
import MenuList from "../components/Dashboard/MenuList";

export default function Dashboard() {
  return (
    <Box
      display="flex"
      flexDirection="column"
      height="100%"
      width={"calc(18vw)"}
      sx={{ bgcolor: "background.paper" }}
    >
      <Logo />
      <MenuList />
    </Box>
  );
}
