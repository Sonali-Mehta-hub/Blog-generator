import org.springframework.beans.factory.annotation.Autowired;are reportedJava
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.model.Blog;
import com.example.service.BlogService;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public Page<Blog> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        return blogService.getBlogs(page, size);
    }

    @GetMapping("/search")
    public Page<Blog> searchBlogs(@RequestParam String title,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        return blogService.searchBlogs(title, page, size);
    }
}
