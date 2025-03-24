import { Heading, Text } from "@radix-ui/themes";
import React, { useEffect } from "react";
import { RYOKO_API_SERVICE } from "../services/ryokoApiService";
import { Activity } from "../interfaces/activity";

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
            <Heading size={"6"}>Activities</Heading>
            <Text>Count of Activities: {activities.length}</Text>
        </React.Fragment>
    );
};
