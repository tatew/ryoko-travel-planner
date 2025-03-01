package tatew.ryoko.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import tatew.ryoko.model.db.Region;

public interface RegionRepository extends CrudRepository<Region, Long>
{
    @Query("SELECT * FROM region WHERE archived_at IS NOT NULL")
    Iterable<Region> findAllArchived();

    @Query("SELECT * FROM region WHERE archived_at IS NULL")
    Iterable<Region> findAllNotArchived();
}
