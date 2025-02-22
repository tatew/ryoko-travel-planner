### Entity Relationship Diagram

```mermaid
---
title: Ryoko Travel Planner
---
erDiagram
    ACTIVITY {
        int id PK
        int address_id FK
        int region_id FK
        text name
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
    }
    ADDRESS {
        int id PK
        text hash
        text street_line1
        text street_Line2
        text state_province
        string state
        text country
        text postcode
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
    ACTIVITY one or more to one ADDRESS : has
    ACTIVITY zero or more to one or zero REGION : located_in
    ACTIVITY one to zero or more ACTIVITY_TAG : "tagged with"
    TAG one to zero or more ACTIVITY_TAG: "applies to"
    
```

### Referneces 

Resource about "tagging" database schemas: [link](http://howto.philippkeller.com/2005/04/24/Tags-Database-schemas/)

SO about options for enums in a db: [link](https://stackoverflow.com/questions/10923213/postgres-enum-data-type-or-check-constraint)