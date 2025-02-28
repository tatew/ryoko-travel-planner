package tatew.ryoko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import tatew.ryoko.exception.GetActivityException;
import tatew.ryoko.model.db.Activity;
import tatew.ryoko.repository.ActivityRepository;

import java.util.NoSuchElementException;

@Configuration
public class ActivityService
{
    @Autowired
    private ActivityRepository activityRepository;

    /**
     * Gets all activities
     *
     * @return All activities
     */
    public Iterable<Activity> getAllActivities()
    {
        return activityRepository.findAll();
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
}
