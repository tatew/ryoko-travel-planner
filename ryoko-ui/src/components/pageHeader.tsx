import { Box, Flex, Heading } from "@radix-ui/themes";
import { IconPlaneDeparture, IconUserCircle } from "@tabler/icons-react";
import { DarkModeToggle } from "./darkModeToggle";

export const PageHeader: React.FC = () => {
    return (
        <Flex justify={"between"} m="2">
            <Box>
                <Flex align="center" gap="2">
                    <IconPlaneDeparture size={64} />
                    <Heading size={"8"}>Ryoko</Heading>
                </Flex>
            </Box>
            <Box>
                <Flex align="center" gap="2">
                    <a>
                        <IconUserCircle size={48} />
                    </a>
                    <DarkModeToggle />
                </Flex>
            </Box>
        </Flex>
    );
};
