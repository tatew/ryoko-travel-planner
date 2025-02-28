package tatew.ryoko.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address
{
    @Column("address_line1")
    private String addressLine1;

    @Column("address_line2")
    private String addressLine2;

    @Column("address_city")
    private String addressCity;

    @Column("address_state")
    private String addressState;

    @Column("address_country")
    private String addressCountry;

    @Column("address_postcode")
    private String addressPostcode;
}
