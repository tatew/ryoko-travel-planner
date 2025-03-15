package tatew.ryoko.exception;

public class CreateActivityException extends Exception
{
    public CreateActivityException(String message, Exception e)
    {
        super(message, e);
    }
}
