import { useTheme } from "next-themes";
import { IconButton } from "@radix-ui/themes";
import { IconSun, IconMoonStars } from "@tabler/icons-react";

export const DarkModeToggle: React.FC = () => {
    const { theme, setTheme } = useTheme();

    const updateTheme = () => {
        theme === "light" ? setTheme("dark") : setTheme("light");
    };

    return (
        <IconButton variant="ghost" size="4" radius="full" onClick={updateTheme}>
            {theme === "light" ? <IconSun /> : <IconMoonStars />}
        </IconButton>
    );
};
