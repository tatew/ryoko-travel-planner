package tatew.ryoko.model.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorDto(List<String> messages, HttpStatus status)
{
}
