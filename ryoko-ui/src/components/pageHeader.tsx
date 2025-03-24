import { Box, Flex, Heading, IconButton, TabNav } from "@radix-ui/themes";
import { IconPlaneDeparture, IconUser, IconUserCircle, IconUserSquare } from "@tabler/icons-react";
import { DarkModeToggle } from "./darkModeToggle";
import { Link, useLocation, useNavigate } from "react-router";

export const PageHeader: React.FC = () => {
    const location = useLocation();
    const currentPath = location.pathname;
    const navigate = useNavigate();

    const clickUserAccount = () => {
        navigate("/account");
    };

    const clickHome = () => {
        navigate("/");
    };

    return (
        <Flex justify="between" align="center" m="2">
            <Box>
                <Flex align="center" gap="2">
                    <IconPlaneDeparture size={64} onClick={clickHome} />

                    <Heading size={"8"}>Ryoko</Heading>
                </Flex>
            </Box>
            <Box>
                <TabNav.Root size={"2"}>
                    <TabNav.Link asChild active={currentPath === "/"}>
                        <Link to="/">Home</Link>
                    </TabNav.Link>
                    <TabNav.Link asChild active={currentPath === "/activities"}>
                        <Link to="/activities">Activities</Link>
                    </TabNav.Link>
                </TabNav.Root>
            </Box>
            <Box>
                <Flex align="center" justify="center" direction="row" gap="4">
                    <DarkModeToggle />
                    <IconButton variant="ghost" radius="full" size="4" onClick={clickUserAccount}>
                        <IconUserCircle size={32} />
                    </IconButton>
                </Flex>
            </Box>
        </Flex>
    );
};
