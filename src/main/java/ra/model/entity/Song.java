package ra.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// tạo bảng tự động trong database nếu bản đó chưa có tên lấy cluôn tênclas
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String songName;
    private String category;
    private String songUrl;

    public Song(Long id, String songName, String category, String songUrl) {
        this.id = id;
        this.songName = songName;
        this.category = category;
        this.songUrl = songUrl;
    }

    public Song() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public void copy(Song song){
        this.id = song.getId();
        this.songName = song.getSongName();
        this.category = song.getCategory();
        this.songUrl = song.getSongUrl();
    }


}
