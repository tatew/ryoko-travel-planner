### Entity Relationship Diagram

```mermaid
---
title: Ryoko Travel Planner
---
erDiagram
    ACTIVITY {
        int id PK
        int region_id FK
        text name
        text phone_number
        text email_address
        bool is_outdoor
        int duration_minutes
        point coordinates
        text notes
        text map_link
        varchar(10) map_provider
        text webside_link
        int cost_bucket
        numeric cost
        varchar(3) currency
        timestamptz created_at
        timestamptz archived_at
        text address_line1
        text address_line2
        text address_city
        text address_state
        text address_country
        text address_postcode
    }
    REGION {
        int id PK
        text name
        text map_link
        varchar(10) map_provider
        timestamptz created_at
        timestamptz archived_at
    }
    TAG {
        int id PK
        text name
    }
    ACTIVITY_TAG {
        int id PK
        int tag_id FK
        int activity_id FK
    }
    ACTIVITY zero or more to one or zero REGION : located_in
    ACTIVITY one to zero or more ACTIVITY_TAG : "tagged with"
    TAG one to zero or more ACTIVITY_TAG: "applies to"

```

### Referneces

Resource about "tagging" database schemas: [link](http://howto.philippkeller.com/2005/04/24/Tags-Database-schemas/)

SO about options for enums in a db: [link](https://stackoverflow.com/questions/10923213/postgres-enum-data-type-or-check-constraint)

List of Java Type mappings to Postgres: [link](https://www.postgresql.org/message-id/AANLkTikkkxN+-UUiGVTzj8jdfS4PdpB8_tDONMFHNqHk@mail.gmail.com)
