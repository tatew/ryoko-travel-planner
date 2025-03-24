import { Heading } from "@radix-ui/themes";
import React from "react";

export const Home: React.FC = () => {
    return (
        <React.Fragment>
            <Heading size={"6"}>Hello from Ryoko</Heading>
            <Heading size={"4"}>An open-source travel planning app</Heading>
        </React.Fragment>
    );
};
