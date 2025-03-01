package tatew.ryoko.model.db;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.postgresql.geometric.PGpoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("activity")
public class Activity
{
    @Id
    private long id;

    @NotNull(message = "Name is required")
    private String name;

    @Column("region_id")
    private long regionId;

    @Column("is_outdoor")
    private boolean isOutdoor;

    @Column("duration_minutes")
    private int durationMinutes;

    private PGpoint coordinates;
    private String notes;

    @Column("map_link")
    private String mapLink;

    @Column("map_provider")
    @Size(max = 10, message = "Map provider must be 10 characters or less")
    private String mapProvider;

    @Column("website_link")
    private String websiteLink;

    @Column("cost_bucket")
    @Max(value = 5, message = "Cost bucket must be between 0 and 5")
    @Min(value = 0, message = "Cost bucket must be between 0 and 5")
    private int costBucket;

    private BigDecimal cost;

    @Size(max = 3, message = "Currency must be 3 characters or less")
    private String currency;

    @Column("created_at")
    @ReadOnlyProperty
    private Timestamp createdAt;

    @Column("archived_at")
    private Timestamp archivedAt;

    @Embedded.Empty
    private Address address;
}

