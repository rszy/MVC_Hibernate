package pl.javastart.minifilmweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/")
    public String movieList(Model model) {
        List<Movie> movies = movieRepository.findAll(); /// ?
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("categories", Category.values());
        return "addForm";
    }

    @PostMapping("/add")
    public String addMovie(Movie movie) {
        movieRepository.save(movie);
        return "redirect:/";
    }

    @GetMapping("/film")
    public String filmDetails(Model model, @RequestParam Long id) {
        Movie movie = movieRepository.findOne(id);
        model.addAttribute("movie", movie);
        model.addAttribute("comment", new Comment());
        return "movieDetails";
    }

}
