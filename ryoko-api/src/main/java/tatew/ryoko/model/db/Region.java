package tatew.ryoko.model.db;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("region")
public class Region
{
    @Id
    private long id;

    @NotNull(message = "Name is required")
    private String name;

    @Column("map_link")
    private String mapLink;

    @Column("map_provider")
    @Size(max = 10, message = "Map provider must be 10 characters or less")
    private String mapProvider;

    @Column("created_at")
    @ReadOnlyProperty
    private Timestamp createdAt;

    @Column("archived_at")
    private Timestamp archivedAt;
}
