package com.steefy.bookstore.service.impl;

import com.steefy.bookstore.dto.FavoriteItemDto;
import com.steefy.bookstore.entity.FavoriteItem;
import com.steefy.bookstore.mapper.FavoriteItemMapper;
import com.steefy.bookstore.repository.FavoriteItemRepository;
import com.steefy.bookstore.service.FavoriteItemService;
import com.steefy.bookstore.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteItemServiceImpl implements FavoriteItemService {

    private final FavoriteItemRepository favoriteItemRepository;
    private final FavoriteItemMapper favoriteItemMapper;

    @Override
    public FavoriteItemDto create(FavoriteItemDto favoriteItemDto) {

        FavoriteItem favoriteItem = favoriteItemMapper.mapToEntity(favoriteItemDto);
        FavoriteItem savedFavoriteItem = favoriteItemRepository.save(favoriteItem);

        return favoriteItemMapper.mapToDto(savedFavoriteItem);
    }

    @Override
    public FavoriteItemDto getById(Long favoriteItemId) {

        FavoriteItem favoriteItem = favoriteItemRepository.findById(favoriteItemId)
        .orElseThrow(() -> new ResourceNotFoundException("FavoriteItem with id(" + favoriteItemId + ") doesn't exist."));

        return favoriteItemMapper.mapToDto(favoriteItem);
    }

    @Override
    public List<FavoriteItemDto> getAll() {

        List<FavoriteItem> categories = favoriteItemRepository.findAll();
        return categories.stream().map((favoriteItem) -> favoriteItemMapper.mapToDto(favoriteItem)).collect(Collectors.toList());
    }

    @Override
    public FavoriteItemDto update(Long favoriteItemId, FavoriteItemDto updatedFavoriteItemDto) {

        FavoriteItem favoriteItem = favoriteItemRepository.findById(favoriteItemId)
        .orElseThrow(() -> new ResourceNotFoundException("FavoriteItem with id(" + favoriteItemId + ") doesn't exist."));

        FavoriteItem updatedFavoriteItem = favoriteItemRepository.save(favoriteItem);

        return favoriteItemMapper.mapToDto(updatedFavoriteItem);
    }

    @Override
    public void delete(Long favoriteItemId) {

        favoriteItemRepository.findById(favoriteItemId)
        .orElseThrow(() -> new ResourceNotFoundException("FavoriteItem with id(" + favoriteItemId + ") doesn't exist."));

        favoriteItemRepository.deleteById(favoriteItemId);
    }

    @Override
    public List<FavoriteItemDto> getAllByUserId(Long userId) {

        List<FavoriteItem> favoriteItems  = favoriteItemRepository.findAllByUserId(userId);
        return favoriteItems.stream().map((favoriteItem) -> favoriteItemMapper.mapToDto(favoriteItem)).collect(Collectors.toList());
    }
}
