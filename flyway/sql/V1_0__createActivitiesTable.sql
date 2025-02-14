CREATE 
    EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE 
    TABLE activities(
        id SERIAL PRIMARY KEY,
        guid UUID DEFAULT uuid_generate_v4(),
        name TEXT NOT NULL,
        coordinates POINT,
        notes TEXT,
        map_link TEXT,
        map_provider VARCHAR(10),
        website_link TEXT,
        cost_bucket INT,
        cost NUMERIC,
        currency VARCHAR(3),
        address_line1 TEXT,
        address_line2 TEXT,
        address_city TEXT,
        address_state TEXT,
        address_country TEXT, 
        address_postcode TEXT, 
        created_at TIMESTAMP, 
        archived_at TIMESTAMP
    );