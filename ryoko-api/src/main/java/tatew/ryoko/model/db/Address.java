package tatew.ryoko.model.db;

import org.springframework.data.relational.core.mapping.Column;

public record Address(
        @Column("address_line1") String addressLine1,
        @Column("address_line2") String addressLine2,
        @Column("address_city") String addressCity,
        @Column("address_state") String addressState,
        @Column("address_country") String addressCountry,
        @Column("address_postcode") String addressPostcode)
{
}
