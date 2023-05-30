package com.kadiraksoy.UnittestAndSpringSecurityProject.service;


import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketResponse;
import com.kadiraksoy.UnittestAndSpringSecurityProject.exception.MisketNotFoundException;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Misket;
import com.kadiraksoy.UnittestAndSpringSecurityProject.repository.MisketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MisketService {

    private final MisketRepository misketRepository;

    public MisketService(MisketRepository misketRepository) {
        this.misketRepository = misketRepository;
    }


    public MisketDto createMisket(MisketDto misketDto){
        Misket misket = new Misket();
        misket.setId(misketDto.getId());
        misket.setName(misketDto.getName());
        misket.setType(misketDto.getType());

        misketRepository.save(misket);

        return misketDto;
    }

    public MisketResponse getAllPokemon(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Misket> miskets = misketRepository.findAll(pageable);
        List<Misket> misketList = miskets.getContent();
        List<MisketDto> content = misketList.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        MisketResponse misketResponse = new MisketResponse();
        misketResponse.setContent(content);
        misketResponse.setPageNo(miskets.getNumber());
        misketResponse.setPageSize(miskets.getSize());
        misketResponse.setTotalElements(miskets.getTotalElements());
        misketResponse.setTotalPages(miskets.getTotalPages());
        misketResponse.setLast(miskets.isLast());

        return misketResponse;
    }

    public MisketDto getMisketById(int id) {
        Misket misket = misketRepository.findById(id).orElseThrow(() ->
                new MisketNotFoundException("Misket could not be found"));
        return mapToDto(misket);
    }

    public MisketDto updateMisket(MisketDto misketDto, int id) {
        Misket misket = misketRepository.findById(id).orElseThrow(() ->
                new MisketNotFoundException("Misket could not be updated"));

        misket.setName(misketDto.getName());
        misket.setType(misketDto.getType());

        Misket updatedMisket = misketRepository.save(misket);
        return mapToDto(updatedMisket);
    }

    public void deleteMisketId(int id) {
        Misket misket= misketRepository.findById(id).orElseThrow(() ->
                new MisketNotFoundException("Misket could not be delete"));
        misketRepository.delete(misket);
    }

    private MisketDto mapToDto(Misket misket) {
        MisketDto misketDto = new MisketDto();
        misketDto.setId(misket.getId());
        misketDto.setName(misket.getName());
        misketDto.setType(misket.getType());
        return misketDto;
    }

    private Misket mapToEntity(MisketDto misketDto) {
        Misket misket = new Misket();
        misket.setName(misketDto.getName());
        misket.setType(misketDto.getType());
        return misket;
    }


}
