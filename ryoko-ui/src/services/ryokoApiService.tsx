import axios, { AxiosInstance } from "axios";
import { Activity } from "../interfaces/activity";

class RyokoApiService {
    api: AxiosInstance;

    constructor() {
        this.api = axios.create({
            baseURL: import.meta.env.VITE_RYOKO_API_BASEURL,
            timeout: 5000
        });
    }

    getActivities = async (): Promise<Activity[]> => {
        const response = await this.api.get("/activities");
        return response.data;
    };
}

export const RYOKO_API_SERVICE = new RyokoApiService();
