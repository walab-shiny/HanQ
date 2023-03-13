import { atom } from "recoil";

export const themeModeState = atom<"light" | "dark">({
  key: "themeMode",
  default: (localStorage.getItem("themeMode") ?? "light") as "light" | "dark",
});
