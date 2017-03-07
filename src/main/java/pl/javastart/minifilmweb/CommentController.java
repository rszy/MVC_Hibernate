package pl.javastart.minifilmweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/comments/add")
    private String addComment(Comment comment, @RequestParam("movie_id") Long movieId) {
        Movie movie = movieRepository.findOne(movieId);
        comment.setMovie(movie);
        commentRepository.save(comment);
        return "redirect:/film?id=" + movieId;
    }

}
