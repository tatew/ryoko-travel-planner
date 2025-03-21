import "@radix-ui/themes/styles.css";
import { Flex, Text, Button } from "@radix-ui/themes";
import { DarkModeToggle } from "./components/darkModeToggle";

export const App: React.FC = () => {
    return (
        <Flex direction="column" gap="2">
            <Text>Hello from Radix Themes :)</Text>
            <Button>Let's go</Button>
            <DarkModeToggle />
        </Flex>
    );
};
