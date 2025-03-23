import { BrowserRouter, Route, Routes } from "react-router";
import { Home } from "./views/home";
import { Activities } from "./views/activities";

export const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/activities" element={<Activities />} />
            </Routes>
        </BrowserRouter>
    );
};
