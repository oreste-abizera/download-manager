package rw.ac.rca.nat2022.server.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.nat2022.server.utils.dtos.WebsiteDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "website")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String website_name;
    private String download_start_date_time;
    private String download_end_date_time;
    private String total_elapsed_time;
    private Long total_downloaded_kilobytes;
    @OneToMany(mappedBy = "website")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Link> links;

    public Website(WebsiteDTO websiteDTO){
        this.website_name = websiteDTO.getName();
        this.download_start_date_time = websiteDTO.getDownloadStartDateTime();
        this.download_end_date_time = websiteDTO.getDownloadEndDateTime();
        this.total_elapsed_time = websiteDTO.getTotalElapsedTime();
        this.total_downloaded_kilobytes = websiteDTO.getTotalDownloadedKilobytes();
    }
}
