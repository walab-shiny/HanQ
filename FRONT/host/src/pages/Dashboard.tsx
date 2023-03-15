import { Box } from "@mui/material";
import CardList from "../components/Dashboard/CardList";
import Header from "../components/Dashboard/Header";
import Logo from "../components/Dashboard/Logo";
import MenuList from "../components/Dashboard/MenuList";

export default function Dashboard() {
  return (
    <Box display="flex" height="100%">
      <Box
        display="flex"
        flexDirection="column"
        width={"calc(18vw)"}
        sx={{ bgcolor: "background.paper" }}
      >
        <Logo />
        <MenuList />
      </Box>
      <Box
        display="flex"
        alignItems="flex-start"
        flexDirection="column"
        gap={1}
      >
        <Header />
        <CardList />
      </Box>
    </Box>
  );
}
