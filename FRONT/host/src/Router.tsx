import { BrowserRouter, Routes, Route } from "react-router-dom";
import ThemeModeToggle from "./components/Header/ThemeModeToggle";
import Main from "./pages/Main";
import Dashboard from "./pages/Dashboard";

function Router() {
  return (
    <BrowserRouter>
      {/* <ThemeModeToggle /> */}
      <Routes>
        <Route path="/" element={<Dashboard />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
