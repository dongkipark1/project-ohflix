package com.project.ohflix.domain.content;

import com.project.ohflix._core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    // ContentUpdateLinkPage
    @Transactional
    public ContentResponse.ContentUpdateLinkPageDTO contentUpdateLinkPageDTO(int userId) {

        Content content = contentRepository.findById(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));

        return new ContentResponse.ContentUpdateLinkPageDTO(content);
    }

    // VideoManagePage
    @Transactional
    public ContentResponse.VideoManagePageDTO videoManagePageDTO() {

        List<Content> contents = contentRepository.findAll();

        return new ContentResponse.VideoManagePageDTO(contents);
    }
}





