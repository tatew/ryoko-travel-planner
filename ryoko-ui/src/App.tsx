import "@radix-ui/themes/styles.css";
import "./app.css";
import React from "react";
import { PageHeader } from "./components/pageHeader";
import { AppRoutes } from "./appRoutes";
import { Container } from "@radix-ui/themes";

export const App: React.FC = () => {
    return (
        <React.Fragment>
            <PageHeader />
            <Container>
                <AppRoutes />
            </Container>
        </React.Fragment>
    );
};
