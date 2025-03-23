import { useTheme } from "next-themes";
import { Switch, Flex } from "@radix-ui/themes";
import { IconSun, IconMoonStars } from "@tabler/icons-react";

export const DarkModeToggle: React.FC = () => {
    const { theme, setTheme } = useTheme();

    return (
        <Flex align="center" gap="2">
            <Switch
                size={"3"}
                defaultChecked={theme === "light"}
                onCheckedChange={(checked) => {
                    setTheme(checked ? "light" : "dark");
                }}
            />
            {theme === "light" ? <IconSun /> : <IconMoonStars />}
        </Flex>
    );
};
