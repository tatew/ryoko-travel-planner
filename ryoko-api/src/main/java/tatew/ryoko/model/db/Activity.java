package tatew.ryoko.model.db;

import jakarta.validation.constraints.NotNull;
import org.postgresql.geometric.PGpoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Table("activity")
public record Activity(
        @Id long id,
        @NotNull String name,
        @Column("is_outdoor") boolean isOutdoor,
        @Column("duration_minutes") int durationMinutes,
        PGpoint coordinates,
        String notes,
        @Column("map_link") String mapLink,
        @Column("map_provider") String mapProvider,
        @Column("website_link") String websiteLink,
        @Column("cost_bucket") int costBucket,
        BigDecimal cost,
        String currency,
        @Column("created_at") @ReadOnlyProperty Timestamp createdAt,
        @Column("archived_at") Timestamp archivedAt,
        @Embedded.Empty Address address)
{
}
