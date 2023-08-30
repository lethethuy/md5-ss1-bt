package ra.model.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ra.model.entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepository implements ISongRespository {
    // Tạo kho quản lý các sesion(phiên ) làm viêc
    private static SessionFactory sessionFactory;
    // entityManager để thao tác với đối tượng cơ sở dữ liệu
    private static EntityManager entityManager;


    // Một khối try catch sử dịng để xử lý các excepton liên quan
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.config.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Song> findAll() {
        List<Song> list = new ArrayList<>();
        String hql = "SELECT s FROM Song AS s";
        // sử dung tự động các phương thức của đối tượng 1 cách tự động
        // Person.class là kiểu dữ liệu được chỉ định sử dụng để thực hiện truy vấn yêu cầu trả về danh sách các đối tượng Person
        TypedQuery<Song> type = entityManager.createQuery(hql, Song.class);
        // lấy về danh sách
        list = type.getResultList();
        return list;
    }

    @Override
    public Song findById(Long id) {
        String hql = "SELECT s FROM Song AS s";
        // sử dung tự động các phương thức của đối tượng 1 cách tự động
        TypedQuery<Song> type = entityManager.createQuery("SELECT s FROM Song AS s WHERE s.id=:id", Song.class);
        type.setParameter("id", id);
        // Lấy về 1 thằng duy nhất
        Song song = type.getSingleResult();
        return song;
    }

    @Override
    public void deleteFindById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            //Mở một phiên làm việc mới bằng cách sử dụng sessionFactory.
            // Một phiên làm việc là một đối tượng Hibernate cho phép bạn tương tác với cơ sở dữ liệu.
            session = sessionFactory.openSession();
            // Bắt đầu một giao dịch trong phiên làm việc.
            // Giao dịch giúp bạn quản lý các thay đổi dữ liệu và đảm bảo tính toàn vẹn của cơ sở dữ liệu.
            transaction = session.beginTransaction();
            session.delete(findById(id));
            // Gọi phương thức để xoá bản ghi tương ứng
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.isActive();
            }
        } finally {
            if (session != null) {
                // đóng phiên giao dịch
                session.close();
            }
        }

    }

    @Override
    public void save(Song song) {
        Session session = null;
        Transaction transaction = null;
        try {
            // khởi tạo 1 session(phiên)
            session = sessionFactory.openSession();
            // bắt đầu 1 giao dịch
            transaction = session.beginTransaction();
            if (song.getId() == null) {
                // chức năng thêm mới
                session.save(song);

            } else {
                // chức năng update (Liên quan đến copy)
                // lấy đối tượng cũ cần sửa ra
                Song old = findById(song.getId());
                if (song.getSongUrl() == null) {
                    song.setSongUrl(old.getSongUrl());
                }
                old.copy(song);
                session.saveOrUpdate(old);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
