package com.example.springlab2.controller.rest;

import com.example.springlab2.dto.GenreDto;
import com.example.springlab2.exception.SwoqeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/genres")
public class GenreRestController extends BaseRestController {

    @GetMapping()
    public Set<GenreDto> findAllGenres(){
        return genreService.findAll();
    }

    @GetMapping("/find/{genreId}")
    public GenreDto findGenreById(@PathVariable String genreId) throws SwoqeException {
        checkNotBlank("genreId", genreId);
        UUID uuid = toUUID(genreId);
        return genreService.findById(uuid);
    }

    @PostMapping("/create")
    public String saveOrUpdateGenre(@RequestBody GenreDto genreDto) {
        genreService.save(genreDto);
        return "redirect:/";
    }

    @PostMapping("/delete/{genreId}")
    public String deleteGenre(@PathVariable String genreId) throws SwoqeException {
        checkNotBlank("genreId", genreId);
        UUID uuid = toUUID(genreId);
        genreService.deleteById(uuid);
        return "redirect:/";
    }


}
