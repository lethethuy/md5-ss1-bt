package ra.model.service;

import ra.model.DTO.SongDTOForm;
import ra.model.entity.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    void save (SongDTOForm s);
    void delete(Long id);
}
