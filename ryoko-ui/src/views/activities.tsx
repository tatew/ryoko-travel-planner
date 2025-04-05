import { Button, Flex, Heading, IconButton, Section, Table, Text, TextField } from "@radix-ui/themes";
import React, { useEffect } from "react";
import { RYOKO_API_SERVICE } from "../services/ryokoApiService";
import { Activity } from "../interfaces/activity";
import getSymbolFromCurrency from "currency-symbol-map";
import { IconPlus, IconSearch, IconSortAscendingLetters } from "@tabler/icons-react";
import { SortableTableHeader } from "../components/sortableTableHeader";

export const Activities: React.FC = () => {
    const [activities, setActivities] = React.useState<Activity[]>([]);

    useEffect(() => {
        RYOKO_API_SERVICE.getActivities()
            .then((response) => {
                setActivities(response);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    return (
        <React.Fragment>
            <Section>
                <Flex justify="between" align="center">
                    <Button size="3">
                        <IconPlus /> New Activity
                    </Button>
                    <TextField.Root placeholder="Search activities..." size={"3"}>
                        <TextField.Slot>
                            <IconSearch size={16} />
                        </TextField.Slot>
                    </TextField.Root>
                </Flex>
            </Section>

            <Table.Root variant="surface">
                <Table.Header>
                    <Table.Row>
                        <SortableTableHeader text="Name" sortType="alphabetic" isActive />
                        <SortableTableHeader text="Cost" sortType="numeric" />
                        <SortableTableHeader text="Notes" />
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {activities.map((activity) => (
                        <Table.Row key={activity.id}>
                            <Table.Cell>
                                <Text>{activity.name}</Text>
                            </Table.Cell>
                            <Table.Cell>
                                <Text>
                                    {activity.cost > 0 ? `${getSymbolFromCurrency(activity.currency)}${activity.cost.toFixed(2)}` : "Free"}
                                </Text>
                            </Table.Cell>
                            <Table.Cell>
                                <Text>{activity.notes || "No notes provided"}</Text>
                            </Table.Cell>
                        </Table.Row>
                    ))}
                    {activities.length === 0 && (
                        <Table.Row>
                            <Table.Cell colSpan={3}>
                                <Text>No activities found.</Text>
                            </Table.Cell>
                        </Table.Row>
                    )}
                </Table.Body>
            </Table.Root>
        </React.Fragment>
    );
};
