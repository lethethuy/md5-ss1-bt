package ra.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ra.model.DTO.SongDTOForm;
import ra.model.entity.Song;
import ra.model.repository.ISongRespository;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service

public class SongService implements ISongService {
    private String pathImage = "/Users/lethethuy/IdeaProjects/module 5/Module5_ss1_baitap1/src/main/webapp/WEB-INF/up/";
    @Autowired
    private ISongRespository songRespository;

    @Override
    public List<Song> findAll() {
        return songRespository.findAll();
    }

    @Override
    public Song findById(Long id) {
        return songRespository.findById(id);
    }

    @Override
    public void save(SongDTOForm s) {
        // Xử lý chuyển đổi
        // upload file
        String filename = null;
        if (!(s.getSongUrl().isEmpty())){
            filename = s.getSongUrl().getOriginalFilename();
            try{
                FileCopyUtils.copy(s.getSongUrl().getBytes(),new File(pathImage+filename));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // Chuyển từ DTO sang thành entity
        Song song = new Song(s.getId(),s.getSongName(),s.getCategory(),filename);
        songRespository.save(song);

    }

    @Override
    public void delete(Long id) {
        songRespository.deleteFindById(id);
    }
}
