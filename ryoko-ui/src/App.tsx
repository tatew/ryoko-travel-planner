import "@radix-ui/themes/styles.css";
import "./app.css";
import React from "react";
import { PageHeader } from "./components/pageHeader";
import { AppRoutes } from "./appRoutes";

export const App: React.FC = () => {
    return (
        <React.Fragment>
            <PageHeader />
            <AppRoutes />
        </React.Fragment>
    );
};
