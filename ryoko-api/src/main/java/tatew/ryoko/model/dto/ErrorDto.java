package tatew.ryoko.model.dto;

import org.springframework.http.HttpStatus;

public record ErrorDto(String message, HttpStatus status)
{
}
