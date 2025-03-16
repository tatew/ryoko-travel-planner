import { useTheme } from "next-themes";
import { Switch, Flex, Box } from "@radix-ui/themes";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSun, faMoon } from "@fortawesome/free-regular-svg-icons";

export default function DarkModeToggle() {
    const { theme, setTheme } = useTheme();

    return (
        <Flex align="center" gap="2">
            <Switch
                defaultChecked={theme === "light"}
                onCheckedChange={(checked) => {
                    setTheme(checked ? "light" : "dark");
                }}
            />
            {theme === "light" ? <FontAwesomeIcon icon={faSun} /> : <FontAwesomeIcon icon={faMoon} />}
        </Flex>
    );
}
