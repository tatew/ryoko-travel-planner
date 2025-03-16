import "@radix-ui/themes/styles.css";
import { Theme } from "@radix-ui/themes";
import { Flex, Text, Button } from "@radix-ui/themes";

function App() {
    return (
        <Theme appearance="dark" accentColor="crimson" grayColor="sand" radius="large" scaling="95%">
            <Flex direction="column" gap="2">
                <Text>Hello from Radix Themes :)</Text>
                <Button>Let's go</Button>
            </Flex>
        </Theme>
    );
}

export default App;
