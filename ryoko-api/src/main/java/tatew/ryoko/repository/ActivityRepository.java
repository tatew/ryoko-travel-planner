package tatew.ryoko.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import tatew.ryoko.model.db.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long>
{
    @Query("SELECT * FROM activity WHERE archived_at IS NOT NULL")
    Iterable<Activity> findAllArchived();

    @Query("SELECT * FROM activity WHERE archived_at IS NULL")
    Iterable<Activity> findAllNotArchived();

    @Query("SELECT * FROM activity WHERE region_id = :regionId AND archived_at IS NOT NULL")
    Iterable<Activity> findAllByRegionIdArchived(long regionId);

    @Query("SELECT * FROM activity WHERE region_id = :regionId AND archived_at IS NULL")
    Iterable<Activity> findAllByRegionIdNotArchived(long regionId);
}
