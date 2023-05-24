package com.ItsTime.ItNovation.controller.movie;

import com.ItsTime.ItNovation.domain.movie.Movie;
import com.ItsTime.ItNovation.service.movie.MovieCrawlService;
import com.ItsTime.ItNovation.service.movie.MovieRepoService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private final MovieCrawlService movieCrawlService;
    @Autowired
    private final MovieRepoService movieRepoService;

    @GetMapping("/api/v1/movies") // 테스트 하실때는 SecurityConfig에 /movies url 추가후 진행하세요!
    public Map<String, Movie> getMovies(Model model) {
        Map<String, Movie> titleAndMovie = movieCrawlService.getTitleAndMovie(); // 이 부분 무조건 고쳐야 함. 동기적으로 데이터 가져와서 스케줄링 같은 작업으로 일정 주기에 끌어오는 방법 고안.
        movieRepoService.saveMovie(titleAndMovie);
        model.addAttribute("movieInfo", titleAndMovie);
        return titleAndMovie;
    }



}
