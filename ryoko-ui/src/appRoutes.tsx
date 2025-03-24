import { Route, Routes } from "react-router";
import { Home } from "./views/home";
import { Activities } from "./views/activities";
import { Account } from "./views/account";

export const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/activities" element={<Activities />} />
            <Route path="/account" element={<Account />} />
        </Routes>
    );
};
