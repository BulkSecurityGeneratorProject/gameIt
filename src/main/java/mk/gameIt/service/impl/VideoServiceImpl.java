package mk.gameIt.service.impl;

import mk.gameIt.domain.Video;
import mk.gameIt.repository.VideoRepository;
import mk.gameIt.service.VideoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service

public class VideoServiceImpl implements VideoService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Video findOne(Long id) {
        return videoRepository.findOne(id);
    }

    @Override
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //Video video = videoRepository.findOne(id);
        //log.debug("Deleted Video: {}", video);
        videoRepository.delete(id);
    }

    @Transactional
    @Override
    public Video save(Video video) {
        video = videoRepository.save(video);
        log.debug("Created Video: {}", video);
        return video;
    }
}
