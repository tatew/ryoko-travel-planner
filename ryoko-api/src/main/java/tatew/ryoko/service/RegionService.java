package tatew.ryoko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import tatew.ryoko.exception.GetRegionException;
import tatew.ryoko.model.db.Region;
import tatew.ryoko.repository.RegionRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;

@Configuration
public class RegionService
{
    @Autowired
    private RegionRepository regionRepository;

    /**
     * Gets all regions
     *
     * @param includeArchived Whether to include archived regions
     * @return All regions
     */
    public Iterable<Region> getAllRegions(boolean includeArchived)
    {
        if (includeArchived)
        {
            return regionRepository.findAll();
        }
        else
        {
            return regionRepository.findAllNotArchived();
        }
    }

    /**
     * Gets a region by ID
     *
     * @param id The ID of the region to return
     * @return The region with the given ID
     * @throws GetRegionException If the region with the given ID does not exist
     */
    public Region getRegionById(long id) throws GetRegionException
    {
        try
        {
            return regionRepository.findById(id).orElseThrow();
        }
        catch (NoSuchElementException e)
        {
            throw new GetRegionException("Region not found", id);
        }
    }

    /**
     * Creates a new region
     *
     * @param region The region to create
     * @return The created region
     */
    public Region createRegion(Region region)
    {
        return regionRepository.save(region);
    }

    /**
     * Archives a region
     *
     * @param id The ID of the region to archive
     * @throws GetRegionException If the region with the given ID does not exist or is already archived
     */
    public void archiveRegion(long id) throws GetRegionException
    {
        Region regionToUpdate = regionRepository.findById(id).orElseThrow(() -> new GetRegionException("Region not found", id));
        if (regionToUpdate.getArchivedAt() != null)
        {
            throw new GetRegionException("Region already archived", id);
        }
        regionToUpdate.setArchivedAt(Timestamp.from(Instant.now()));
        regionRepository.save(regionToUpdate);
    }

    /**
     * Deletes a region
     *
     * @param id The ID of the region to delete
     * @throws GetRegionException If the region with the given ID does not exist
     */
    public void deleteRegion(long id) throws GetRegionException
    {
        Region regionToDelete = regionRepository.findById(id).orElseThrow(() -> new GetRegionException("Region not found", id));
        regionRepository.delete(regionToDelete);
    }
}
