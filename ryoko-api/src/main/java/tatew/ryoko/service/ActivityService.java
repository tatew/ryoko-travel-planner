package tatew.ryoko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import tatew.ryoko.exception.GetActivityException;
import tatew.ryoko.exception.GetRegionException;
import tatew.ryoko.model.db.Activity;
import tatew.ryoko.repository.ActivityRepository;
import tatew.ryoko.repository.RegionRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;

@Configuration
public class ActivityService
{
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private RegionRepository regionRepository;

    /**
     * Gets all activities
     *
     * @param archived Whether to include archived activities
     * @return All activities
     */
    public Iterable<Activity> getAllActivities(boolean archived)
    {
        return archived ? activityRepository.findAllArchived() : activityRepository.findAllNotArchived();
    }

    /**
     * Gets an activity by ID
     *
     * @param id The ID of the activity to return
     * @return The activity with the given ID
     * @throws GetActivityException If the activity with the given ID does not exist
     */
    public Activity getActivityById(long id) throws GetActivityException
    {
        try
        {
            return activityRepository.findById(id).orElseThrow();
        }
        catch (NoSuchElementException e)
        {
            throw new GetActivityException("Activity not found", id);
        }
    }

    /**
     * Gets all activities for a region
     *
     * @param regionId The ID of the region to get activities for
     * @param archived Whether to include archived activities
     * @return All activities for the region with the given ID
     * @throws GetRegionException If the region with the given ID does not exist
     */
    public Iterable<Activity> getActivitiesByRegionId(long regionId, boolean archived) throws GetRegionException
    {
        if (!regionRepository.existsById(regionId))
        {
            throw new GetRegionException("Region not found", regionId);
        }
        return archived ? activityRepository.findAllByRegionIdArchived(regionId) : activityRepository.findAllByRegionIdNotArchived(regionId);
    }

    /**
     * Creates a new activity
     *
     * @param activity The activity to create
     * @return The created activity
     */
    public Activity createActivity(Activity activity)
    {
        return activityRepository.save(activity);
    }

    /**
     * Archives an activity
     *
     * @param id The ID of the activity to archive
     * @throws GetActivityException If the activity with the given ID does not exist or is already archived
     */
    public void archiveActivity(long id) throws GetActivityException
    {
        Activity activityToUpdate = activityRepository.findById(id).orElseThrow(() -> new GetActivityException("Activity not found", id));
        if (activityToUpdate.getArchivedAt() != null)
        {
            throw new GetActivityException("Activity already archived", id);
        }
        activityToUpdate.setArchivedAt(Timestamp.from(Instant.now()));
        activityRepository.save(activityToUpdate);
    }

    /**
     * Deletes an activity
     *
     * @param id The ID of the activity to delete
     * @throws GetActivityException If the activity with the given ID does not exist
     */
    public void deleteActivity(long id) throws GetActivityException
    {
        Activity activityToDelete = activityRepository.findById(id).orElseThrow(() -> new GetActivityException("Activity not found", id));
        activityRepository.delete(activityToDelete);
    }
}
