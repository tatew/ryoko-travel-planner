import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { App } from "./App.tsx";
import { Theme } from "@radix-ui/themes";
import { ThemeProvider } from "next-themes";
import { BrowserRouter } from "react-router";

createRoot(document.getElementById("root")!).render(
    <StrictMode>
        <ThemeProvider attribute={"class"}>
            <Theme accentColor="sky" grayColor="sage" radius="large" scaling="95%">
                <BrowserRouter>
                    <App />
                </BrowserRouter>
            </Theme>
        </ThemeProvider>
    </StrictMode>
);
