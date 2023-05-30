package com.kadiraksoy.UnittestAndSpringSecurityProject.controller;

import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketResponse;
import com.kadiraksoy.UnittestAndSpringSecurityProject.service.MisketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class MisketController {

    private final MisketService misketService;

    public MisketController(MisketService misketService) {
        this.misketService = misketService;
    }
    @GetMapping("misket")
    public ResponseEntity<MisketResponse> getMiskets(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(misketService.getAllMisket(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("misket/{id}")
    public ResponseEntity<MisketDto> misketDetail(@PathVariable int id) {
        return ResponseEntity.ok(misketService.getMisketById(id));

    }

    @PostMapping("misket/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MisketDto> createMisket(@RequestBody MisketDto misketDto) {
        return new ResponseEntity<>(misketService.createMisket(misketDto), HttpStatus.CREATED);
    }

    @PutMapping("misket/{id}/update")
    public ResponseEntity<MisketDto> updateMisket(@RequestBody MisketDto misketDto, @PathVariable("id") int misketId) {
        MisketDto response = misketService.updateMisket(misketDto, misketId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("misket/{id}/delete")
    public ResponseEntity<String> deleteMisket(@PathVariable("id") int misketId) {
        misketService.deleteMisketId(misketId);
        return new ResponseEntity<>("Misket delete", HttpStatus.OK);
    }
}
