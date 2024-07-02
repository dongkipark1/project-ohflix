package com.project.ohflix.domain.mylist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ohflix._core.error.exception.Exception400;
import com.project.ohflix._core.error.exception.Exception404;
import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRepository;
import com.project.ohflix.domain.content.ContentRequest;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import com.project.ohflix.domain.watchingHistory.WatchingHistory;
import com.project.ohflix.domain.watchingHistory.WatchingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MyListService {

    private final MyListRepository myListRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final WatchingHistoryRepository watchingHistoryRepository;
    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper;

    @Value("${custom.openai.key}")
    private String openaiKey;


    @Transactional
    public MyListResponse.MyListDTO findMyListById(Integer sessionUserId) {
        // 헤더 유저 가져오기 ( 프로필 아이콘 )
        User user = userRepository.findUserProfileById(sessionUserId);

        List<MyList> myFavoriteList = myListRepository.findMyListByUserId(sessionUserId);
        List<MyList> myWatchList = myListRepository.findMyWatchListByUserId(sessionUserId);

        return new MyListResponse.MyListDTO(user, myFavoriteList, myWatchList);
    }

    // 찜 기능
    @Transactional
    public MyListResponse.AddFavoriteDTO addFavorite(MyListRequest.AddFavoriteDTO reqDTO) {
        if (myListRepository.findByUserIdAndContentId(reqDTO.getUserId(), reqDTO.getContentId()).isPresent()) {
            throw new Exception400("이미 찜 되어있는 컨텐츠입니다.");
        }

        User user = userRepository.findById(reqDTO.getUserId()).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Content content = contentRepository.findById(reqDTO.getContentId()).orElseThrow(() -> new Exception404("해당하는 컨텐츠를 찾을 수 없습니다."));

        MyList myList = new MyList();
        myList.setUser(user);
        myList.setContent(content);
        myList.setWatchOrFav(WatchOrFav.FAVORITE);
        myListRepository.save(myList);

        boolean isFavorite = true;
        isFavorite(isFavorite);

        return new MyListResponse.AddFavoriteDTO(reqDTO);
    }

    // 찜 취소 기능
    @Transactional
    public MyListResponse.RemoveFavoriteDTO removeFavorite(MyListRequest.RemoveFavoriteDTO reqDTO) {
        Optional<MyList> favorite = myListRepository.findByUserIdAndContentId(reqDTO.getUserId(), reqDTO.getContentId());
        if (favorite.isPresent()) {
            myListRepository.delete(favorite.get());
            boolean isFavorite = false;
            isFavorite(isFavorite);
            return new MyListResponse.RemoveFavoriteDTO(reqDTO);
        } else {
            throw new Exception404("해당 컨텐츠가 존재하지 않습니다.");
        }
    }

    // 찜 여부
    public MyListResponse.FavoriteCheck isFavorite(boolean value) {
        boolean isFavorite = value;

        return new MyListResponse.FavoriteCheck(isFavorite);
    }

    //영화 시청한 시간 저장하기
    @Transactional
    public void savePlayedTime(ContentRequest.VideoProgressDTO videoProgressDTO, Integer sessionUserId) {
        User user = userRepository.findById(sessionUserId).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Content content = contentRepository.findByVideoPath(videoProgressDTO.getFilename()).orElseThrow(() -> new Exception404("해당하는 컨텐츠를 찾을 수 없습니다."));
        MyList myList = myListRepository.findMyListByUserIdAndContentIdAndWatch(user.getId(), content.getId()).orElse(null);
        System.out.println("myList = " + myList);

        //시청기록 저장
        watchingHistoryRepository.save(videoProgressDTO.toWatcingHistoryEntity(user, content, videoProgressDTO));

        if (myList != null){
            myListRepository.deleteByUserIdAndContentId(user.getId(), content.getId());
            myListRepository.save(videoProgressDTO.toMyListEntity(user, content, videoProgressDTO));
        }else {
            myListRepository.save(videoProgressDTO.toMyListEntity(user, content, videoProgressDTO));
        }
    }

    //영화 시청한 시간 가져오기
    @Transactional
    public Double getPlayedTime(Integer sessionUserId, String filename) {
        User user = userRepository.findById(sessionUserId).orElseThrow(() -> new Exception404("해당하는 사용자를 찾을 수 없습니다."));
        Content content = contentRepository.findByVideoPath(filename).orElseThrow(() -> new Exception404("해당하는 컨텐츠를 찾을 수 없습니다."));
        MyList myList = myListRepository.findMyListByUserIdAndContentIdAndWatch(user.getId(), content.getId()).orElse(null);
        System.out.println("myList = " + myList);
        if (myList != null){
            return myList.getPlayedTime();
        }else {
            return 0.0;
        }
    }

    //openai
    public List<Content> getOpenAi() {
        List<WatchingHistory> watchingHistories = watchingHistoryRepository.findByUserId(2);
        List<Content> contents = contentRepository.findAll();
        List<MyListRequest.WatchingHistoryDTO> watchingHistoryRequest = watchingHistories.stream().map(MyListRequest.WatchingHistoryDTO::new).toList();
        List<MyListRequest.ContentDTO> contentRequest = contents.stream().map(MyListRequest.ContentDTO::new).toList();

        // Convert the requests to JSON strings
        String watchingHistoryJson = null;
        String contentJson = null;
        try {
            watchingHistoryJson = objectMapper.writeValueAsString(watchingHistoryRequest);
            contentJson = objectMapper.writeValueAsString(contentRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // 예외 스택 트레이스를 출력하여 디버깅 정보를 얻습니다.
        }

        // Create the OpenAI request with the JSON strings
        String userMessage = String.format("Here is the watching history: %s. Based on this watching history and the available contents: %s, please recommend 5 movies and provide their ids and titles only.", watchingHistoryJson, contentJson);
        MyListRequest.OpenAIRequest openAIRequest = new MyListRequest.OpenAIRequest("gpt-3.5-turbo-0125", userMessage);
        List<MyListResponse.ContentDTO> recommendedMovies = processOpenAIRequest(openAIRequest).getBody();
        List<Content> responseDTO = new ArrayList<>();
        for(MyListResponse.ContentDTO recommendedMovie:recommendedMovies){
            Content content=contentRepository.findById(recommendedMovie.getId()).orElseThrow(() -> new Exception404("찾으시는 영화가 없습니다"));
            responseDTO.add(content);
        }
        return responseDTO;
    }
    //restapi 처리할거
    public ResponseEntity<List<MyListResponse.ContentDTO>> processOpenAIRequest(MyListRequest.OpenAIRequest openAIRequest) {
        try {
            // 메시지 생성
            ObjectNode messageNode = objectMapper.createObjectNode();
            messageNode.put("role", "user");
            messageNode.put("content", openAIRequest.getMessage());

            // 요청 본문 생성
            ObjectNode requestBodyNode = objectMapper.createObjectNode();
            requestBodyNode.put("model", openAIRequest.getModel());
            requestBodyNode.set("messages", objectMapper.createArrayNode().add(messageNode));

            String requestBody = objectMapper.writeValueAsString(requestBodyNode);

            // 요청 본문 출력 (디버깅용)
            System.out.println("Request Body: " + requestBody);

            // RestTemplate 인스턴스 생성
            RestTemplate restTemplate = new RestTemplate();

            System.out.println("👉👉👉👉👉👉" + openaiKey);

            // 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openaiKey);

            // 요청 엔터티 생성
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            // OpenAI API 호출
            ResponseEntity<String> response = restTemplate.exchange(
                    OPENAI_API_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // 응답 본문 출력 (디버깅용)
            System.out.println("Response Body: " + response.getBody());

            // 응답 본문 파싱
            String responseBody = response.getBody();
            List<MyListResponse.ContentDTO> recommendedMovies = new ArrayList<>();
            if (responseBody != null) {
                // 응답 본문이 JSON 형식이 아닌 경우 텍스트 형식으로 처리
                if (responseBody.startsWith("{")) {
                    // JSON 형식인 경우
                    recommendedMovies = parseRecommendedMovies(responseBody);
                } else {
                    // 텍스트 형식인 경우
                    recommendedMovies = parseRecommendedMoviesFromText(responseBody);
                }
            }

            // 추천 영화 목록 출력
            recommendedMovies.forEach(movie -> System.out.println("Recommended Movie: " + movie));

            return ResponseEntity.ok(recommendedMovies);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //openai json 형식의 응답을 파싱
    List<MyListResponse.ContentDTO> parseRecommendedMovies(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode choices = root.path("choices");
            if (choices.isArray() && choices.size() > 0) {
                JsonNode messageContent = choices.get(0).path("message").path("content");
                // JSON 파싱 중 발생할 수 있는 예외를 처리합니다.
                try {
                    return parseRecommendedMoviesFromText(messageContent.textValue());
                } catch (Exception e) {
                    System.err.println("Error parsing JSON content: " + messageContent.textValue());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // 기본 빈 리스트 반환
    }

    //openai 텍스트 형식의 응답을 파싱
    List<MyListResponse.ContentDTO> parseRecommendedMoviesFromText(String responseBody) {
        // 텍스트 형식의 응답을 파싱하여 추천 영화를 추출합니다.
        List<MyListResponse.ContentDTO> recommendedMovies = new ArrayList<>();
        try {
            Pattern pattern = Pattern.compile("\\{\"id\":(\\d+),\"contentTitle\":\"([^\"]+)\"}");
            Matcher matcher = pattern.matcher(responseBody);

            while (matcher.find()) {
                int id = Integer.parseInt(matcher.group(1));
                String title = matcher.group(2);
                recommendedMovies.add(new MyListResponse.ContentDTO(id, title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendedMovies;
    }


}





