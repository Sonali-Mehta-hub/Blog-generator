import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    // Search blogs by title (case-insensitive)
    Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
