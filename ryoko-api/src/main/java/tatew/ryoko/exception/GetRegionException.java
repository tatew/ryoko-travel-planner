package tatew.ryoko.exception;

import lombok.Getter;

@Getter
public class GetRegionException extends RuntimeException
{
    private long regionId;

    public GetRegionException(String message)
    {
        super(message);
    }

    public GetRegionException(String message, long regionId)
    {
        super(message);
        this.regionId = regionId;
    }
}
