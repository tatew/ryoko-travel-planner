package tatew.ryoko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import tatew.ryoko.exception.GetActivityException;
import tatew.ryoko.model.db.Activity;
import tatew.ryoko.repository.ActivityRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;

@Configuration
public class ActivityService
{
    @Autowired
    private ActivityRepository activityRepository;

    /**
     * Gets all activities
     *
     * @param includeArchived Whether to include archived activities
     * @return All activities
     */
    public Iterable<Activity> getAllActivities(boolean includeArchived)
    {
        if (includeArchived)
        {
            return activityRepository.findAll();
        }
        else
        {
            return activityRepository.findAllNotArchived();
        }
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
