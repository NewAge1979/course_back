package pl.anigram.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import pl.anigram.backend.model.entity.User;

@Tag(name = "authentication", description = "Регистрация (авторизация) пользователей.")
public interface AuthController {
    @Operation(summary = "Регистрация нового пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь создан."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка при создании нового пользователя.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<?> signUp(User user);
}