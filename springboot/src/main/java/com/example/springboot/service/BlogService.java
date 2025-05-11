import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.model.Blog;
import com.example.repository.BlogRepository;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Page<Blog> getBlogs(int page, int size) {
        return blogRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Blog> searchBlogs(String title, int page, int size) {
        return blogRepository.findByTitleContainingIgnoreCase(title, PageRequest.of(page, size));
    }

    // Add other methods like save, delete, getById as needed
}
