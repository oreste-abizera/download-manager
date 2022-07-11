package rw.ac.rca.nat2022.server.services;

import rw.ac.rca.nat2022.server.models.Link;
import rw.ac.rca.nat2022.server.utils.dtos.LinkDTO;

import java.util.List;

public interface ILinkService {
    List<Link> getAllLinks();
    Link save(LinkDTO linkDTO);
    Link update(Long id, LinkDTO linkDTO);
    Link getLinkById(Long id);
    void deleteLinkById(Long id);
}
