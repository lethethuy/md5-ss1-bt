package ra.model.DTO;

import org.springframework.web.multipart.MultipartFile;

public class SongDTOForm {
    private Long id;
    private String songName;
    private String category;
    private MultipartFile songUrl;

    public SongDTOForm() {
    }

    public SongDTOForm(Long id, String songName, String category, MultipartFile songUrl) {
        this.id = id;
        this.songName = songName;
        this.category = category;
        this.songUrl = songUrl;
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

    public MultipartFile getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(MultipartFile songUrl) {
        this.songUrl = songUrl;
    }
}
