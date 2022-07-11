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
public class Link {
    private Long id;
    private String link_name;
    private String total_elapsed_time;
    private String total_downloaded_kilobytes;

    private Website website;
}
