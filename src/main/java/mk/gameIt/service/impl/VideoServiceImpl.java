package mk.gameIt.service.impl;

import mk.gameIt.domain.Video;
import mk.gameIt.repository.VideoRepository;
import mk.gameIt.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service

public class VideoServiceImpl implements VideoService {
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
        videoRepository.delete(id);
    }
    @Transactional
    @Override
    public Video save(Video video) {
        return videoRepository.save(video);
    }
}
