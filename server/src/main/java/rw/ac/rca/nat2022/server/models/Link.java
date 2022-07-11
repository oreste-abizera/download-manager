package rw.ac.rca.nat2022.server.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.nat2022.server.utils.dtos.LinkDTO;

import javax.persistence.*;

@Entity
@Table(name = "link")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String link_name;
    private String total_elapsed_time;
    private String total_downloaded_kilobytes;
    @ManyToOne
    @JoinColumn(name = "website_id", nullable = false)
    private Website website;

    public Link(LinkDTO linkDTO){
        this.setLink_name(linkDTO.getLinkName());
        this.setTotal_elapsed_time(linkDTO.getTotalElapsedTime());
        this.setTotal_downloaded_kilobytes(linkDTO.getTotalDownloadedKilobytes());
    }
}
