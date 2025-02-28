package tatew.ryoko.model.db;

import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @Column("is_outdoor")
    private boolean isOutdoor;

    @Column("duration_minutes")
    private int durationMinutes;

    private PGpoint coordinates;
    private String notes;

    @Column("map_link")
    private String mapLink;

    @Column("map_provider")
    private String mapProvider;

    @Column("website_link")
    private String websiteLink;

    @Column("cost_bucket")
    private int costBucket;

    private BigDecimal cost;
    private String currency;

    @Column("created_at")
    @ReadOnlyProperty
    private Timestamp createdAt;

    @Column("archived_at")
    private Timestamp archivedAt;

    @Embedded.Empty
    private Address address;
}