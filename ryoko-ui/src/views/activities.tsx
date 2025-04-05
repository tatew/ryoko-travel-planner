import { Heading, Section, Table, Text } from "@radix-ui/themes";
import React, { useEffect } from "react";
import { RYOKO_API_SERVICE } from "../services/ryokoApiService";
import { Activity } from "../interfaces/activity";
import getSymbolFromCurrency from "currency-symbol-map";

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
                <Heading size={"6"}>Activities</Heading>
            </Section>

            <Table.Root variant="surface">
                <Table.Header>
                    <Table.Row>
                        <Table.ColumnHeaderCell>Name</Table.ColumnHeaderCell>
                        <Table.ColumnHeaderCell>Cost</Table.ColumnHeaderCell>
                        <Table.ColumnHeaderCell>Notes</Table.ColumnHeaderCell>
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
