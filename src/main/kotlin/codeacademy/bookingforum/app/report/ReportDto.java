package codeacademy.bookingforum.app.report;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    Long id;
    @NotEmpty(message = "Please specify a reason for this report.")
    String reason;
    @NotEmpty(message = "Please describe the issue.")
    String description;
    LocalDateTime reportDate;
    Long userId;
    Long postId;
    Long commentId;
}
