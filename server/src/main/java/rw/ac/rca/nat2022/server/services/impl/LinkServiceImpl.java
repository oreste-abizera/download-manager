package rw.ac.rca.nat2022.server.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.Link;
import rw.ac.rca.nat2022.server.repositories.ILinkRepository;
import rw.ac.rca.nat2022.server.services.ILinkService;
import rw.ac.rca.nat2022.server.utils.dtos.LinkDTO;

import java.util.List;

@Service
public class LinkServiceImpl implements ILinkService {
    private final ILinkRepository linkRepository;

    public LinkServiceImpl(ILinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    @Override
    public Link save(LinkDTO linkDTO) {
        return linkRepository.save(new Link(linkDTO));
    }

    @Override
    public Link update(Long id, LinkDTO linkDTO) {
        return null;
    }

    @Override
    public Link getLinkById(Long id) {
        return null;
    }

    @Override
    public void deleteLinkById(Long id) {

    }
}
