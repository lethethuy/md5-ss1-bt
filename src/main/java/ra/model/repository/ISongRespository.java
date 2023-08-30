package ra.model.repository;

import ra.model.entity.Song;

import java.util.List;

public interface ISongRespository {
    List<Song> findAll();
    Song findById(Long id);
    void deleteFindById(Long id);
    void save(Song song);

}
