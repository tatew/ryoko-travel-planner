import { Address } from "./address";
import { PgPoint } from "./pgPoint";

export interface Activity {
    id: number;
    name: string;
    regionId: number;
    isOutdoor: boolean;
    durationMinutes: number;
    coordinates: PgPoint;
    notes: string;
    phoneNumber: string;
    emailAddress: string;
    mapLink: string;
    mapProvider: string;
    websiteLink: string;
    costBucket: number;
    cost: number;
    currency: string;
    createdAt: Date;
    archivedAt: Date;
    address: Address;
}
