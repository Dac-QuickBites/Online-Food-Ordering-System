package backend.foodiesapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogEntryDTO {
    private String level;
    private String source;
    private String userId;
    private String action;
    private String message;
    private String stackTrace;
}
