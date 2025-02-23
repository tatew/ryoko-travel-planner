CREATE 
    TABLE region (
        id SERIAL PRIMARY KEY,
        name TEXT,
        map_link TEXT,
        map_provider VARCHAR(10),
        created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
        archived_at TIMESTAMPTZ
    )

CREATE 
    TABLE activity (
        id SERIAL PRIMARY KEY,
        region_id INTEGER REFERENCES region 
        name TEXT NOT NULL,
        is_outdoor BOOLEAN,
        duration_minutes INTEGER
        coordinates POINT,
        notes TEXT,
        map_link TEXT,
        map_provider VARCHAR(10),
        website_link TEXT,
        cost_bucket INTEGER,
        cost NUMERIC,
        currency VARCHAR(3),
        created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL, 
        archived_at TIMESTAMPTZ,
        address_line1 TEXT,
        address_line2 TEXT,
        address_city TEXT,
        address_state TEXT,
        address_country TEXT,
        address_postcode TEXT
    );