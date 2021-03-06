package rw.ac.rca.nat2022.server.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkDTO {
    private Long id;
    private String linkName;
    private Long totalElapsedTime;
    private Long totalDownloadedKilobytes;
    private Long websiteId;
}
