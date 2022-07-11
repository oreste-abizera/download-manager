package rw.ac.rca.nat2022.server.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebsiteDTO {
    private Long id;
    private String name;
    private String downloadStartDateTime;
    private String downloadEndDateTime;
    private String totalElapsedTime;
    private Long totalDownloadedKilobytes;
}
