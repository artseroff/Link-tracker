package edu.java.bot.api.controller;

import edu.java.request.LinkUpdateRequest;
import edu.java.response.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Tag(name = "update")
@RequestMapping("/update")
public class UpdateController {
    @Operation(summary = "Отправить обновление")
    @ApiResponse(responseCode = "200", description = "Обновление обработано")
    @ApiResponse(
        responseCode = "400",
        description = "Некорректные параметры запроса",
        content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
    @ApiResponse(
        responseCode = "500",
        description = "Ошибка сервера",
        content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
    @PostMapping()
    public ResponseEntity<Void> processUpdate(@Valid @RequestBody LinkUpdateRequest request) {
        return ResponseEntity.ok().build();
    }
}
