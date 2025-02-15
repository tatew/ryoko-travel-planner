package tatew.ryoko.model.db;

import jakarta.validation.constraints.NotNull;
import org.postgresql.geometric.PGpoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Table("activities")
public record Activity(
        @Id long id,
        @ReadOnlyProperty UUID uuid,
        @NotNull String name,
        PGpoint coordinates,
        @Column("map_link") String mapLink,
        @Column("map_provider") String mapProvider,
        @Column("website_link") String websiteLink,
        @Column("cost_bucket") String costBucket,
        BigDecimal cost,
        String currency,
        @Column("address_line1") String addressLine1,
        @Column("address_line2") String addressLine2,
        @Column("address_city") String addressCity,
        @Column("address_state") String addressState,
        @Column("address_country") String addressCountry,
        @Column("address_postcode") String addressPostcode,
        @Column("created_at") @ReadOnlyProperty Timestamp createdAt,
        @Column("archived_at") Timestamp archivedAt)
{
}
