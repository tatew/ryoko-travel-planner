package tatew.ryoko.exception;

import lombok.Getter;

@Getter
public class GetActivityException extends Exception
{
    private long activityId;

    public GetActivityException(String message)
    {
        super(message);
    }

    public GetActivityException(String message, long activityId)
    {
        super(message);
        this.activityId = activityId;
    }
}
