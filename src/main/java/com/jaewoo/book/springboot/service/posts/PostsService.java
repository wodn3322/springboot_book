package com.jaewoo.book.springboot.service.posts;

import com.jaewoo.book.springboot.domain.posts.Posts;
import com.jaewoo.book.springboot.domain.posts.PostsRepository;
import com.jaewoo.book.springboot.web.dto.PostsResponseDto;
import com.jaewoo.book.springboot.web.dto.PostsSaveRequestDto;
import com.jaewoo.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    @SneakyThrows
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalAccessException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
