import { Flex, IconButton, Table, Text } from "@radix-ui/themes";
import { IconSortAscending2, IconSortAscendingLetters, IconSortAscendingNumbers } from "@tabler/icons-react";
import React from "react";

interface SortableTableHeaderProps {
    text: string;
    onClick?: () => void;
    isActive?: boolean;
    sortType?: "alphabetic" | "numeric" | "other";
}

export const SortableTableHeader: React.FC<SortableTableHeaderProps> = (props: SortableTableHeaderProps) => {
    const { text, onClick, isActive, sortType } = props;

    const iconElement =
        sortType === "alphabetic" ? (
            <IconSortAscendingLetters />
        ) : sortType === "numeric" ? (
            <IconSortAscendingNumbers />
        ) : (
            <IconSortAscending2 />
        );

    const variant = isActive ? "solid" : "surface";

    return (
        <Table.ColumnHeaderCell>
            <Flex justify="start" gap="2" align="center">
                {sortType && (
                    <IconButton variant={variant} onClick={onClick}>
                        {iconElement}
                    </IconButton>
                )}
                <Text size="5">{text}</Text>
            </Flex>
        </Table.ColumnHeaderCell>
    );
};
