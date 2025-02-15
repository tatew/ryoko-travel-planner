package tatew.ryoko.repository;

import org.springframework.data.repository.CrudRepository;
import tatew.ryoko.model.db.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long>
{
}
