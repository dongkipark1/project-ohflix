package com.project.ohflix.domain.content;

import com.project.ohflix._core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;

    // main page data
    public ContentResponse.MainPageDTO getMainPageData() {
        // 제일 인기많은 영상을 메인페이지 대문으로 뿌리기
        PageRequest pickOne = PageRequest.of(0, 1);
        Content mostViewed = contentRepository.findOneMostViewed(pickOne).getContent().get(0);

        // Pageable 객체를 사용해 딱 10개만 반환 -> TOP 10
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Content> top10List = contentRepository.findTop10(pageRequest);

        // 최신 컨텐츠도 딱 10개만 로드하고 오른쪽으로 넘길때는 Ajax로 요청하기
        List<Content> newList = contentRepository.findNewVideos(pageRequest);

        // navbar 신규 콘텐츠
        PageRequest fiveItems = PageRequest.of(0, 5);
        List<Content> navbarItems = contentRepository.findNewFive(fiveItems);

        return new ContentResponse.MainPageDTO(mostViewed, top10List, newList, navbarItems);
    }

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

    //
    public List<ContentResponse.LatestContentDTO> findLatestContent() {
        List<Content> latestContentList = contentRepository.findLatestContent();
        System.out.println(latestContentList);

        return latestContentList.stream().map(content
                -> new ContentResponse.LatestContentDTO(content)).toList();
    }

    // 영화 상세정보 페이지 데이터
    public ContentResponse.DetailsDTO getContentDetails(Integer contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));

        return new ContentResponse.DetailsDTO(content);
    }

    // 메인 페이지 영화 상세정보 가져오는 모달 - 비동기 통신
    public ContentResponse.DetailsDTO getContentInfo(Integer contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));
        return new ContentResponse.DetailsDTO(content);
    }

    public void saveContent(ContentRequest.AdminUploadDTO requestDTO) {
        contentRepository.save(requestDTO.toEntity());
    }
}





