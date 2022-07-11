package rw.ac.rca.nat2022.client.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Website {
    private Long id;
    private String website_name;
    private Long download_start_date_time;
    private Long download_end_date_time;
    private Long total_elapsed_time;
    private String f_download_start_date_time;
    private String f_download_end_date_time;
    private String f_total_elapsed_time;
    private Long total_downloaded_kilobytes;

    private Set<Link> links = new HashSet<>();
}
