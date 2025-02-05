---- 생성 순서 : 1번, 프로필 아이콘 더미
INSERT INTO profile_icon_tb (path, name, main, most_use, funny, popular, jooho_child_first, jooho_child_second)
VALUES
    ('/static/images/profiles/netflix-profile.png', 'Icon 1', true, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile2.png', 'Icon 2', false, true, false, true, false, false),
    ('/static/images/profiles/netflix-profile3.png', 'Icon 3', false, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile4.png', 'Icon 4', true, true, false, true, false, false),
    ('/static/images/profiles/netflix-profile5.png', 'Icon 5', false, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile6.png', 'Icon 6', true, false, false, true, false, false),
    ('/static/images/profiles/netflix-profile7.png', 'Icon 7', false, true, true, false, false, false),
    ('/static/images/profiles/netflix-profile8.png', 'Icon 8', true, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile9.png', 'Icon 9', false, true, false, false, false, false),
    ('/static/images/profiles/netflix-profile10.png', 'Icon 10', true, false, false, true, false, false),
--     C3반 친구들 더미
    ('/static/images/profiles/netflix-profile31.png', 'Icon 31', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile32.png', 'Icon 32', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile33.png', 'Icon 33', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile34.png', 'Icon 34', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile35.png', 'Icon 35', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile36.png', 'Icon 36', false, false, false, false, false, true),
    ('/static/images/profiles/netflix-profile37.png', 'Icon 37', false, false, false, false, false, true),
    ('/static/images/profiles/netflix-profile38.png', 'Icon 38', false, false, false, false, false, true),
    ('/static/images/profiles/netflix-profile39.png', 'Icon 39', false, false, false, false, false, true),
    ('/static/images/profiles/netflix-profile40.png', 'Icon 40', false, false, false, false, false, true);

-- 생성 순서 : 2번, 사용자 더미 1번사용자는 오플릭스 관리자.
INSERT INTO user_tb (email, password, nickname, name, mobile, profile_icon_id, status, user_save_rate, is_kids, login_save, is_auto_play, is_subscribe, created_at)
VALUES
    ('admin@example.com', '1234', 'adminUser', '관리자', '010-1111-1111', 1, 'ADMIN', 'R', false, true, true, true, NOW()),
    ('user2@example.com', '1234', '김성훈', '김성훈', '010-2222-2222', 11, 'USER', 'ALL', false, false, true, true, NOW()),
    ('user3@example.com', '1234', '박동기', '박동기', '010-3333-3333', 3, 'USER', 'ALL', true, false, true, false, NOW()),
    ('user4@example.com', '1234', '공지영', '공지영', '010-4444-4444', 4, 'USER', 'PG', false, true, false, true, NOW()),
    ('user5@example.com', '1234', '양승호', '양승호', '010-5555-5555', 5, 'USER', 'ALL', false, false, true, false, NOW()),
    ('user6@example.com', '1234', '최윤정', '최윤정', '010-6666-6666', 6, 'USER', 'PG', true, true, false, true, NOW()),
    ('user7@example.com', '1234', '최주호', '최주호', '010-7777-7777', 7, 'USER', 'ALL', false, false, false, true, NOW()),
    ('user8@example.com', '1234', '홍길동', '홍길동', '010-8888-8888', 8, 'USER', 'PG', true, false, true, false, NOW()),
    ('user9@example.com', '1234', '김철수', '김철수', '010-9999-9999', 9, 'USER', 'ALL', false, true, false, true, NOW()),
    ('user10@example.com', '1234', '강유리', '강유리', '010-1111-0000', 10, 'USER','R', true, false, false, false, NOW());

-- 생성 순서 : 3번, 카드 정보 더미
INSERT INTO card_info_tb (user_id, card_number, last_digit, expiry_month, card_owner, date_of_birth, is_main, is_agreed_third)
VALUES
    (2, '1234567812345678', '5678', '12/23', 'User2', '1990-01-01', TRUE, TRUE),
    (2, '3334567812340078', '0078', '05/25', 'User2', '1990-01-01', FALSE, TRUE),
    (3, '8765432187654321', '4321', '11/22', 'User3', '1985-02-02', TRUE, FALSE),
    (4, '1111222233334444', '4444', '10/21', 'User4', '1975-03-03', TRUE, TRUE),
    (5, '5555666677778888', '8888', '09/20', 'User5', '2000-04-04', TRUE, TRUE);


--- 생성 순서 : 4번, content 테이블
INSERT INTO content_tb (
    real_play_time,title, thumbnail, video_path, main_photo, poster_photo, text_photo,
    director, introduction, characteristic, play_time, product_year, writers,
    actors, view_count, rate, genre, created_at
) VALUES (145,'레디 플레이어 원', '/static/images/dummy/ready_player_one/thumbnail.webp', 'videolocation/ready_player_one/ready_player_one.mpd', '/static/images/dummy/ready_player_one/main-photo.webp', '/static/images/dummy/ready_player_one/poster-photo.jpg', '/static/images/dummy/ready_player_one/text-photo.webp', '스티븐 스필버그', '붕괴 직전의 암울한 세상. 재능 있는 게이머가 거대한 가상 현실 세계의 소유권을 얻기 위한 도전에 앞장선다. 모두의 꿈과 희망을 지키기 위해!', '상상의 나라, 흥미진진', '2시간 20분', '2018', '자크 펜, 어니스트 클라인', '타이 쉐리던, 올리비아 쿡, 벤 멘델슨, 리나 웨이츠, T.J. 밀러, 사이먼 페그, 마크 라이런스, 필립 자오, 모리사키 윈, 해나 존카먼', 3, 'ALL', 'SF', NOW()),
         (145, '메이즈 러너','/static/images/dummy/maze_runner/thumbnail.webp','videolocation/maze_runner/main-maze_runner.mpd','/static/images/dummy/maze_runner/main-photo.webp','/static/images/dummy/maze_runner/poster-photo.jpg','/static/images/dummy/maze_runner/text-photo.webp','웨스 볼','알 수 없는 곳에서, 기억을 잃은 채 무리 지어 살아가는 소년들. 그들이 어디서 온 누구지, 여기는 어디지 아무도 알지 못한다. 단 확실한 건, 이곳에서 탈출하려면 밤마다 괴성이 들리는 거대한 미로를 통과해야 한다는 것!','아드레날린 폭발, 긴장감 넘치는','1시간 54분','2014','노아 오페넘, 그랜트 피어스 마이어스, T.S. 나울린', '딜런 오브라이언, 카야 스코델라리오, 토마스 브로디생스터, 윌 폴터, 에멜 아민, 이기홍, 덱스터 다든, 퍼트리샤 클락슨', 5,'PG','SF', NOW()),
         (141, '블레이드러너2049','/static/images/dummy/blade_runner/thumbnail.webp','videolocation/blade_runner/blade_runner.mpd','/static/images/dummy/blade_runner/main-photo.webp','/static/images/dummy/blade_runner/poster-photo.webp','/static/images/dummy/blade_runner/text-photo.webp','드니 빌뇌브','수십 년 만에 모습을 드러낸 유골에는 어떤 비밀이 숨어 있었나. 진실을 찾으려는 경찰, 진실을 덮으려는 조직, 진실을 악용하려는 재계 거물의 쫓고 쫓기는 게임이 시작된다!','발상의 전환, 어두운','2시간 43분','2017','햄프턴 펜처, 마이클 그린','라이언 고슬링, 해리슨 포드, 아나 데 아르마스, 실비아 획스, 로빈 라이트, 매켄지 데이비스, 재러드 레토, 데이브 바티스타, 칼라 주리, 레니 제임스, 손 옥, 에드워드 제임스 올모스',6,'PG','SF', NOW()),
         (145, '인터스텔라','/static/images/dummy/interstella/thumbnail.webp','videolocation/interstella/interstella.mpd','/static/images/dummy/interstella/main-photo.webp','/static/images/dummy/interstella/poster-photo.jpg','/static/images/dummy/interstella/text-photo.webp','크리스토퍼 놀란','인류 멸망이 목전에 닥친 미래. 우주비행사들이 웜홀을 통해 광활한 우주를 여행하며 인류가 살 수 있는 또 다른 행성을 찾아 나선다.','발상의 전환','2시간 49분','2014','조나선 놀란, 크리스토퍼 놀란','매튜 매커너히, 앤 해서웨이, 제시카 차스테인, 마이클 케인, 엘렌 버스틴, 존 리스고, 케이시 애플렉, 웨스 벤틀리, 빌 어윈, 매켄지 포이, 토퍼 그레이스, 데이빗 가야시',  2,'ALL','SF', NOW()),
         (168, '아틀라스','/static/images/dummy/atlas/thumbnail.jpg','videolocation/atlas/atlas.mpd','/static/images/dummy/atlas/main-photo.webp','/static/images/dummy/atlas/poster-photo.jpg','/static/images/dummy/atlas/text-photo.webp','브래드 페이튼','치명적인 AI 반란이 일어난 지 수십 년 후, 탁월한 데이터 분석가(제니퍼 로페즈)가 인류를 구할 유일한 희망이 바로 그 AI 기술에 달려 있다는 사실을 깨닫는다.','발상의 전환, 긴박감 넘치는','2시간','2024','레오 사다리안, 에런 얼라이 콜라이트','제니퍼 로페즈, 시무 류, 스털링 K. 브라운, 그레고리 제임스 코인, 마크 스트롱, 에이브러햄 포플라, 라나 파리야',  2 ,'PG','SF', now()),
         (102, '센과 치히로의 행방불명','/static/images/dummy/chihiro/thumbnail.webp','videolocation/chihiro/chihiro.mpd','/static/images/dummy/chihiro/main-photo.jpg','/static/images/dummy/chihiro/poster-photo.png','/static/images/dummy/chihiro/text-photo.webp','미야자키 하야오','마녀가 지배하는 신비한 세계에 발을 들여놓은 치히로, 마녀에게 거역하는 자는 동물로 변하게 되는데…','상상의 나래, 진심 어린','2시간 5분','2001','미야자키 하야오','히이라기 루미, 이리노 미유, 나쓰키 마리, 나이토 다카시, 사와구치 야스코, 가슈인 다쓰야, 카미키 류노스케, 다마이 유미, 오오이즈미 요, 스카와라 분타, 하야시코바',  2 ,'ALL','ANIME', now()),
         (29, '존윅','/static/images/dummy/john_wick/thumbnail.webp','videolocation/john_wick/john_wick.mpd','/static/images/dummy/john_wick/main-photo.webp','/static/images/dummy/john_wick/poster-photo.jpg','/static/images/dummy/john_wick/text-photo.webp','채드 스타헬스키','어느 조직 보스의 아들이 존 윅의 차를 훔치고 그의 반려견도 죽인다. 그러자 세상 무서울 것 없는 전직 킬러 존 윅은 조직 전체를 상대로 피비린내 나는 복수를 시작한다.','폭력적인, 어두운','1시간 41분','2014','데릭 콜스태드','키아누 리브스, 미카엘 니크비스트, 앨피 앨런, 윌럼 더포, 딘 윈터스, 아드리안 팔리키, 오메르 바르네아, 토비 레너드 무어, 다니엘 베른하르트, 브리짓 모이나한, 존 레귀자모, 이언 맥셰인, 데이비드 패트릭 켈리, 케빈 내쉬',2,'R','ACTION',NOW()),
         (110, '고양이의 보은','/static/images/dummy/thecatreturns/thumbnail.webp','videolocation/thecatreturns/thecatreturns.mpd','/static/images/dummy/thecatreturns/main-photo.jpg','/static/images/dummy/thecatreturns/poster-photo.png','/static/images/dummy/thecatreturns/text-photo.webp','모리타 히로유키','평범한 여고생 하루는 하굣길에 트럭에 치일 뻔한 고양이를 구해준다. 그런데 알고 보니 고양이 왕국의 왕자님이었다니! 보답으로 하루를 신부로 맞이하겠다고 하는데, 아무리 왕국이 멋지고 좋아도 평생 고양이로 살 순 없지 않을까.','유쾌 발랄, 상상의 나래, 기분 좋아지는','1시간 15분','2002','모리타 히로유키','이케와키 치즈루, 하카마다 요시히코, 마에다 아키, 야마다 타카유키, 사토 히토미, 사토이 겐타, 하마다 마리, 와타나베 데쓰, 사이토 요스케, 오카에 구미코, 단바 데쓰로',   2,'ALL','ANIME', NOW()),
         (29, '어느 일란성 세 쌍둥이의 재회', '/static/images/dummy/three_twins/thumbnail.webp', 'videolocation/three_twins/three_twins.mpd', '/static/images/dummy/three_twins/main-photo.webp', '/static/images/dummy/three_twins/poster-photo.jpg', '/static/images/dummy/three_twins/text-photo.webp', '팀 워들', '우연히 만난 세 명의 십 대 소년. 자신들이 갓난아기 때 헤어진 일란성 세쌍둥이 형제란 사실을 알고 크나큰 기쁨에 젖지만, 곧 충격적인 진실이 드러난다.', '어두운', '1시간 37분', '2018', '팀 워들', null, 2, 'PG', 'DOCUMENTARY', NOW()),
         (75, '도라에몽: 스탠바이미','/static/images/dummy/doraemon/thumbnail.webp','videolocation/doraemon/doraemon.mpd','/static/images/dummy/doraemon/thumbnail.webp','/static/images/dummy/doraemon/poster-photo.png','/static/images/dummy/doraemon/text-photo.webp','야기 류이치, 야마자키 타카시','초등학교 4학년생 노진구는 둘도 없는 친구인 고양이 로봇 도라에몽이 미래로 돌아갈 수 있도록 착한 아이가 되고자 노력한다.','기분 좋아지는, 진심 어린','1시간 36분','2014','야기 류이치, 야마자키 타카시', '미즈타 와사비, 오하라 메구미, 카카즈 유미, 스바루 키무라, 세키 토모카즈, 츠마부키 사토시', 3,'ALL','ANIME', now());
INSERT INTO content_tb (
    real_play_time,title, thumbnail, video_path, main_photo, poster_photo, text_photo,
    director, introduction, characteristic, play_time, product_year, writers,
    actors, view_count, rate, genre, created_at
) VALUES(0,'극장판 포켓몬스터: 너로 정했다!','/static/images/dummy/poketmonster/thumbnail.webp','videolocation/poketmonster/poketmonster.mpd','/static/images/dummy/poketmonster/main-photo.webp','/static/images/dummy/poketmonster/poster-photo.png','/static/images/dummy/poketmonster/text-photo.webp','유야마 구니히코','포켓몬을 받는 날인데 그만 늦잠을 잤네. 결국 마지막으로 남겨진 피카츄와 파트너가 된 지우. 피카츄의 고집 때문에 한바탕한 뒤 전설의 포켓몬 칠색조를 목격한다. 우리, 이 포켓몬을 찾으러 가볼래? 칠색조를 만나서 배틀을 해보고 싶어.','상상의 나래, 흥미진진','1시간36분','2017','유야마 구니히코','오타니 이쿠에, 사라 나토체니, 데이비드 올리버 넬슨, 마이크 폴록, 케이트 브리스틀, 수지 마이어스, 로저 파슨스',  5 ,'ALL','ANIME', NOW()),
        (75, '몬스터 주식회사','/static/images/dummy/monster_inc/thumbnail.webp','videolocation/monster_inc/monster_inc.mpd','/static/images/dummy/monster_inc/main-photo.webp','/static/images/dummy/monster_inc/poster-photo.png','/static/images/dummy/monster_inc/text-photo.webp','피트 닥터','아이들의 비명소리로 에너지를 만들어내는 몬스터 주식회사. 덩치 큰 설리(존 굳맨)와 수다쟁이 마이크(빌리 크리스탈)는 아이들 겁주기로 가장 인정받는 팀이다. 그러나 실수로 부라는 어린이가 몬스터 세계로 들어온 뒤, 부를 집으로 보내주기 위한 몬스터들의 좌충우돌 모험이 시작된다.','상상의 나래,  흥미진진','1시간 34분','2001','피트 닥터','존 굿맨, 빌리 크리스탈, 메리 깁스, 스티브 부세미, 제임스 코번, 제니퍼 틸리',  10 ,'ALL','ANIME', NOW()),
        (133, '나 홀로 집에','/static/images/dummy/home_alone/thumbnail.webp','videolocation/home_alone/home_alone.mpd','/static/images/dummy/home_alone/main-photo.webp','/static/images/dummy/home_alone/poster-photo.png','/static/images/dummy/home_alone/text-photo.webp','크리스 콜럼버스','가족이 다 사라졌으면 좋겠어! 하나님이 소원을 들어주신 걸까? 크리스마스 연휴에 집에 혼자 남게 된 8살 소년. 혼자 놀기에 지칠 즈음, 집 주위를 얼쩡거리는 도둑들을 발견하는데. 들어올 테면 들어와 보시지. 우리 집은 내가 지킨다!','허당 매력','1시간 43분','1990','크리스 콜럼버스','맥컬리 컬킨, 조 페시, 다니엘 스턴, 존 허드, 로버츠 블로섬, 캐서린 오하라, 앤젤라 고설스, 데빈 러트레이, 게리 배먼, 힐러리 울프',   3 ,'ALL','COMEDY', NOW()),
        (60, '악마는 프라다를 입는다','/static/images/dummy/devil_wears_prada/thumbnail.webp','videolocation/devil_wears_prada/devil_wears_prada.mpd','/static/images/dummy/devil_wears_prada/main-photo.webp','/static/images/dummy/devil_wears_prada/poster-photo.png','/static/images/dummy/devil_wears_prada/text-photo.webp','데이비드 프랭클','깐깐한 패션지 편집장의 비서로 일하게 된 사회 초년생. 성공을 위해 고군분투하는 동시에, 자신만의 강점과 스타일을 찾아나간다.','위트 있는, 기분 좋아지는','1시간 49분','2006','얼린 브로시 매케나','앤 해서웨이, 메릴 스트립, 에밀리 블런트, 스탠리 투치, 사이먼 베이커, 에이드리언 그레니에이, 트레이시 톰스, 재클린 티퍼니 브라운, 리치 서머, 다니엘 선자타', 10,'PG','COMEDY', NOW()),
        (66,'행오버','/static/images/dummy/hangover/thumbnail.webp','videolocation/hangover/hangover.mpd','/static/images/dummy/hangover/main-photo.webp','/static/images/dummy/hangover/poster-photo.png','/static/images/dummy/hangover/text-photo.webp','토드 필립스','라스베이거스에서 벌인 광란의 총각 파티 후 다음 날 아침. 절친 셋이 숙취에 시달리며 깨어나 보니 결혼식을 앞둔 친구가 사라졌다! 전날 일은 하나도 기억이 안 나는데 어쩌지?','아찔하고 발칙하게','1시간 39분','2009','스콧 무어, 존 루커스','브래들리 쿠퍼, 에드 헬름스, 잭 갤리퍼내키스, 저스틴 바사, 헤더 그레이엄, 사샤 바레즈, 제프리 탬보어, 켄 정, 레이철 해리스, 마이크 타이슨', 23,'R','COMEDY', NOW()),
        (145, '화이트칙스','/static/images/dummy/white_chicks/thumbnail.webp','videolocation/white_chicks/white_chicks.mpd','/static/images/dummy/white_chicks/main-photo.webp','/static/images/dummy/white_chicks/poster-photo.png','/static/images/dummy/white_chicks/text-photo.webp','키넌 아이보리 웨이언스','납치 음모를 저지해야 하는 두 흑인 FBI 요원. 자신들이 경호를 맡은 상속녀 시늉을 하려고 백인 여자로 변장한다.','아찔하고 발칙하게, 허당 매력','1시간 49분','2004','키넌 아이보리 웨이언스, 숀 웨이언스, 말런 웨이언스, 앤드루 매켈프레시, 마이클 앤서니 스노든, 제이비어 쿡','숀 웨이언스, 말런 웨이언스, 제이미 킹, 프랭키 페이슨, 로슬린 먼로, 존 허드, 비지 필립스, 테리 크루스, 브리트니 다니엘, 에디 베레즈, 메이틀랜드 워드',22,'PG','COMEDY', NOW()),
        (60, '찰리와 초콜릿 공장','/static/images/dummy/charlie_chocolate/thumbnail.webp','videolocation/charlie_chocolate/charlie_chocolate.mpd','/static/images/dummy/charlie_chocolate/main-photo.webp','/static/images/dummy/charlie_chocolate/poster-photo.png','/static/images/dummy/charlie_chocolate/text-photo.webp','팀 버튼','괴짜 윌리 웡카의 초콜릿 공장을 견학하게 된 행운의 어린이 5명은 그가 만들어내는 놀라운 과자의 비밀을 알게 된다.','유쾌 발랄, 상상의 나래','1시54분','2005','존 오거스트','조니 뎁, 프레디 하이모어, 데이비드 켈리, 헬레나 본햄 카터, 노아 테일러, 미시 파일, 제임스 폭스, 딥 로이, 크리스토퍼 리, 아담 고들리, 안나소피아 롭, 줄리아 윈터, 조던 프라이, 필립 위그래츠', 3,'ALL','COMEDY', NOW()),
        (89, '유전','/static/images/dummy/hereditary/thumbnail.webp','videolocation/hereditary/hereditary.mpd','/static/images/dummy/hereditary/main-photo.webp','/static/images/dummy/hereditary/poster-photo.png','/static/images/dummy/hereditary/text-photo.webp','아리 애스터','"애니"는 일주일 전 돌아가신 어머니의 유령이 집에 나타나는 것을 느낀다. 애니가 어머니와 닮았다며 접근한 수상한 이웃 "조앤"을 통해 엄마의 비밀을 발견하고, 자신이 엄마와 똑같은 일을 저질렀음을 알게 된다. 그리고 마침내 애니의 엄마로부터 시작돼 딸 "찰리"와 아들 "피터"에게까지 이어진 저주의 실체가 정체를 드러내는데···','소름돋는, 긴박한','2시간 7분','2018','아리 애스터','토니 콜렛, 밀리 샤피로, 알렉스 울프', 1,'PG','HORROR', NOW()),
        (101, '부산행','/static/images/dummy/train_to_busan/thumbnail.webp','videolocation/train_to_busan/train_to_busan.mpd','/static/images/dummy/train_to_busan/main-photo.jpg','/static/images/dummy/train_to_busan/poster-photo.webp','/static/images/dummy/train_to_busan/text-photo.webp','연상호','좀비 바이러스가 온 나라를 휩쓸면서, 단 하나 남은 안전한 도시로 가기 위해 딸과 함께 열차에 몸을 실은 남자. 살아남기 위한 극한의 사투가 시작된다.','긴박감 넘치는, 긴장감 넘치는','1시간 57분','2016','박주석','공유, 정유미, 마동석, 최우식, 안소희 등',20 ,'PG','HORROR', NOW()),
        (112, '랑종','/static/images/dummy/rangjong/thumbnail.webp','videolocation/rangjong/rangjong.mpd','/static/images/dummy/rangjong/main-photo.webp','/static/images/dummy/rangjong/poster-photo.webp','/static/images/dummy/rangjong/text-photo.webp','반종 피산다나쿤','한 여성이 갈수록 이상한 행동을 하자, 어두운 비밀을 밝혀야 하는 무당. 가문에 얽힌 불길한 오랜 저주를 마주해야 한다.','으스스한, 불길한, 무서운','2시간 10분','2021','나홍진','나릴야 군몽콘켓, 싸와니 우툼마, 씨라니 얀키띠칸',  1,'R','HORROR',NOW());
INSERT INTO content_tb (
    real_play_time, title, thumbnail, video_path, main_photo, poster_photo, text_photo,
    director, introduction, characteristic, play_time, product_year, writers,
    actors, view_count, rate, genre, created_at
) VALUES (0, '월드 워 Z','/static/images/dummy/wolrd_war_z/thumbnail.webp','videolocation/wolrd_war_z/main-photo.webp','/static/images/dummy/wolrd_war_z/main-photo.webp','/static/images/dummy/wolrd_war_z/poster-photo.webp','/static/images/dummy/wolrd_war_z/text-photo.webp','마크 포스터','인간을 괴물로 만드는 바이러스의 습격을 다룬 재난 스릴러 영화. 《Variety》가 ‘상상력과 몰입감이 돋보이는 새로운 작품이 좀비 영화 대열에 합류했다. ’라고 호평했다.','긴박감 넘치는, 폭력적인, 무서운','1시간 57분','2013','매튜 마이클 캐너핸, 드류 고다드','브래드 피트, 미레유 에노스',  10 ,'PG','HORROR',NOW()),
         (52, '곤지암','/static/images/dummy/gonziam/thumbnail.webp','videolocation/gonziam/gonziam.mpd','/static/images/dummy/gonziam/main-photo.webp','/static/images/dummy/gonziam/poster-photo.webp','/static/images/dummy/gonziam/text-photo.webp','정범식','환자의 집단 자살과 병원장 실종 이후로 버려진 곤지암 정신병원. 흉가로 유명한 이곳에 체험단 7명이 인터넷 방송을 위해 발을 들인다. 이들의 목표는, 열리지 않는 402호에 들어가는 것. 하지만 촬영 시작부터 괴담이 현실이 된다.','으스스한, 어두운, 무서운','1시간 34분','2018','정범식','위하준, 박지현, 오아연, 박성훈',  3 ,'PG','HORROR',NOW()),
         (144, '조커','/static/images/dummy/joker/thumbnail.webp','videolocation/joker/joker.mpd','/static/images/dummy/joker/main-photo.webp','/static/images/dummy/joker/poster-photo.webp','/static/images/dummy/joker/text-photo.webp','토드 필립스','1981년 고담시. 정신과 병력이 있는 코미디언 지망생이 대중의 관심을 얻으려 고군분투한다. 어느 날 자신을 공격한 이들에게 반격을 가하는데 그로부터 삶이 어둡고 처참한 방향으로 치닫기 시작한다.','폭력적인, 어두운','2시간 1분','2019','토드 필립스','호아킨 피닉스, 로버트 드 니로',  3 ,'PG','THRILLER',NOW()),
         (95, '스마트폰을 떨어뜨렸을 뿐인데','/static/images/dummy/smartphone_drop/thumbnail.jpg','videolocation/smartphone_drop/smartphone_drop.mpd','/static/images/dummy/smartphone_drop/main-photo.webp','/static/images/dummy/smartphone_drop/poster-photo.webp','/static/images/dummy/smartphone_drop/text-photo.webp','김태준','젊은 여성의 스마트폰을 손에 넣은 살인범. 스마트폰을 이용해 그녀의 신분을 도용하고 삶을 갉아먹기 시작한다. 손에 땀을 쥐게 하는 스릴러.','긴장감 넘치는','1시간 57분','2023','김태준','천우희, 임시완, 김희원',  2 ,'PG','THRILLER',NOW()),
         (22, '기생수','/static/images/dummy/parasites/thumbnail.webp','videolocation/parasites/parasites.mpd','/static/images/dummy/parasites/main-photo.webp','/static/images/dummy/parasites/poster-photo.webp','/static/images/dummy/parasites/text-photo.webp','야마자키 타카시','인간이 굶주린 기생생물의 제물이 되자, 한 소년이 인류를 구하기 위해 자기 몸에 침투한 생명체와 공생 관계를 형성한다.','색다른, 무서운, 긴장감 넘치는','1시간 49분','2014','야마자키 타카시','소메타니 쇼타, 후카츠 에리, 아베 사다오, 하시모토 아이, 히가시데 마사히로, 이와이 히데토, 야마나카 타카시, 오쿠이 슈지, 이케우치 만사쿠, 도요하라 고스케, 오모리 나오, 키타무라 카즈키, 요 키미코, 쿠니무라 준, 아사노 타다노부',  2 ,'R','ACTION',NOW()),
         (29, '나의 문어 선생님','/static/images/dummy/octopus_teacher/thumbnail.jpg','videolocation/octopus_teacher/octopus_teacher.mpd','/static/images/dummy/octopus_teacher/main-photo.webp','/static/images/dummy/octopus_teacher/poster-photo.jpg','/static/images/dummy/octopus_teacher/text-photo.webp','피파 얼릭, 제임스 리드','영리한 문어와 뜻밖의 우정을 나누는 영화감독의 이야기를 감동적으로 그린 다큐멘터리.','힐링, 진실을 찾아','1시간 25분','2020','피파 얼릭, 제임스 리드',null,null,'ALL','DOCUMENTARY',NOW()),
         (150, '다크나이트','/static/images/dummy/darknight/thumbnail.webp','videolocation/darknight/darknight.mpd','/static/images/dummy/darknight/main-photo.webp','/static/images/dummy/darknight/poster-photo.jpg','/static/images/dummy/darknight/text-photo.webp','크리스토퍼 놀란','배트맨과 고든 형사 그리고 지방 검사가 고담 시의 지하 범죄 조직을 해체하려고 하지만 새로운 악당은 그들의 계획을 끊임없이 방해한다.','어두운, 흥미진진','2시간 32분 ','2008','조너선 놀런, 크리스토퍼 놀란, 데이비드 S.고이어','크리스천 베일, 히스 레저, 아론 에크하트, 마이클 케인, 매기 질렌할, 게리 올드먼, 모건 프리먼, 모닉 커넌',2,'PG','ACTION',NOW()),
         (30, '도둑들','/static/images/dummy/thieves/thumbnail.png','videolocation/thieves/thieves.mpd','/static/images/dummy/thieves/main-photo.webp','/static/images/dummy/thieves/poster-photo.jpg','/static/images/dummy/thieves/text-photo.png','최동훈','한국과 홍콩에서 내로라하는 최고의 꾼들이 뭉쳤다. 목표는 마카오 카지노에서 희대의 다이아몬드 ‘태양의 눈물’을 빼오는 것. 목적은 다르지만 목표는 같은 각양각색 10인의 도둑이 일생일대의 도박을 시작한다.','아드레날린 폭발, 긴장감 넘치는, 흥미진진','2시간 15분','2012','최동훈, 이기철','김윤석, 이정재, 김혜수, 전지현, 임달화, 김해숙, 오달수, 김수현, 중국상, 리신제, 기국서, 최덕문, 채국희, 예수정',  2 ,'PG','ACTION',NOW()),
         (101, '언노운: 우주를 보는 타임머신','/static/images/dummy/unknown/thumbnail.jpg','videolocation/unknown/unknown.mpd','/static/images/dummy/unknown/main-photo.webp','/static/images/dummy/unknown/poster-photo.webp','/static/images/dummy/unknown/text-photo.webp','샤이 갈','먼 은하계의 모습을 놀랍도록 생생하게 담은 사진으로 우리의 상상력을 자극하는 제임스 웹 우주 망원경. 최초 구상 단계부터 발사까지, 그 역사적인 여정을 기록한 다큐멘터리.','진실을 찾아','1시간 4분','2023','샤이 갈',null,2,'ALL','DOCUMENTARY',NOW()),
         (95, '살인자의 기억법','/static/images/dummy/killers_memory/thumbnail.webp','videolocation/killers_memory/killers_memory.mpd','/static/images/dummy/killers_memory/main-photo.webp','/static/images/dummy/killers_memory/poster-photo.webp','/static/images/dummy/killers_memory/text-photo.webp','원신연','과거 연쇄 살인을 저질렀던 병수. 알츠하이머에 걸려 기억이 점점 희미해진다. 녹음기에 의지해 사라지는 기억을 붙들어 두던 어느 날, 딸의 새 남자친구를 만난다. 하지만 살인자는 살인자를 알아보는 법. 이놈한테서 분명 피 냄새가 나.','심리, 긴장감 넘치는','1시간 58분','2017','황조윤, 원신연','설경구, 김남길, 김설현',  2 ,'PG','THRILLER',NOW());
INSERT INTO content_tb (
    real_play_time, title, thumbnail, video_path, main_photo, poster_photo, text_photo,director, introduction,
    characteristic, play_time, product_year, writers, actors, view_count, rate, genre, created_at
) VALUES (143, '올드보이','/static/images/dummy/oldboy/thumbnail.webp','videolocation/oldboy/oldboy.mpd','/static/images/dummy/oldboy/main-photo.webp','/static/images/dummy/oldboy/poster-photo.webp','/static/images/dummy/oldboy/text-photo.webp','박찬욱','내가 여기 왜 들어온 걸까. 영문도 모른 채 감금됐던 남자가 15년 만에 풀려났다. 그리고 시작된 5일간의 추적. 복수를 원하는가? 그렇다면 누가 왜 가뒀는지 비밀을 풀어라.','발상의 전환, 폭력적인, 어두운','2시간','2003','박찬욱, 임준형, 황조윤','최민식, 유지태, 강혜', 3  ,'R','THRILLER',NOW()),
         (100, '헤어질 결심','/static/images/dummy/decision_to_leave/thumbnail.png','videolocation/decision_to_leave/decision_to_leave.mpd','/static/images/dummy/decision_to_leave/main-photo.png','/static/images/dummy/decision_to_leave/poster-photo.png','/static/images/dummy/decision_to_leave/text-photo.png','박찬욱','산 정상에서 추락한 한 남자의 변사 사건. 담당 형사 "해준"(박해일)은 사망자의 아내 "서래"(탕웨이)와 마주하게 된다. "산에 가서 안 오면 걱정했어요, 마침내 죽을까 봐." 남편의 죽음 앞에서 특별한 동요를 보이지 않는 "서래". 경찰은 보통의 유가족과는 다른 "서래"를 용의선상에 올린다. "해준"은 사건 당일의 알리바이 탐문과 신문, 잠복수사를 통해 "서래"를 알아가면서 그녀에 대한 관심이 점점 커져가는 것을 느낀다. 한편, 좀처럼 속을 짐작하기 어려운 "서래"는 상대가 자신을 의심한다는 것을 알면서도 조금의 망설임도 없이 "해준"을 대하는데…. 진심을 숨기는 용의자 용의자에게 의심과 관심을 동시에 느끼는 형사 그들의 <헤어질 결심>','거부할 수 없는 이끌림, 긴장감 넘치는, 로맨틱','2시간 18분','2022','박찬욱, 정서경','탕웨이, 박해일',  2 ,'PG','ROMANCE',NOW()),
         (147, '어바웃 타임','/static/images/dummy/about_time/thumbnail.png','videolocation/about_time/about_time.mpd','/static/images/dummy/about_time/main-photo.png','/static/images/dummy/about_time/poster-photo.png','/static/images/dummy/about_time/text-photo.png','리처드 커티스','평범한 청년이 알게 된 가문의 놀라운 비밀. 그건 바로 집안의 남자들에게 시간을 되돌리는 능력이 있다는 것. 청년은 첫눈에 반한 여인의 마음을 얻기 위해 그 특별한 능력을 사용하기로 한다. 그리고 그녀와의 꿈 같은 시간이 시작된다.','힐링, 설렘주의, 위트 있는','2시간 6분','2013','리처드 커티스','도널드 글리슨, 레이첼 맥아담스, 빌 나이, 마고 로비, 리디아 윌슨, 린제이 던칸, 리처드 코더리, 톰 홀랜더, 조슈아 맥과이어, 바네사 커비',  3 ,'PG','ROMANCE',NOW()),
         (108, '남자가 사랑할때','/static/images/dummy/when_a_man_loves/thumbnail.png','videolocation/when_a_man_loves/when_a_man_loves.mpd','/static/images/dummy/when_a_man_loves/main-photo.png','/static/images/dummy/when_a_man_loves/poster-photo.png','/static/images/dummy/when_a_man_loves/text-photo.png','한동욱','나이만 먹었을 뿐, 대책 없는 이 남자, 아직도 형 집에 얹혀 살며 조카한테 삥 뜯기는 이 남자, 빌려준 돈은 기필코 받아오는 이 남자, 목사라고 인정사정 봐 주지 않는 이 남자, 여자한테 다가갈 땐 바지부터 내리고 보는 막무가내 이 남자, 평생 사랑과는 거리가 멀었던 한 남자가 사랑에 눈 뜨다! 일생에 단 한번, 남자가 사랑할 때','감상적, 감정을 파고드는, 로맨틱','1시간 59분','2014','유갑열','황정민, 한혜진, 곽도원, 정만식, 김혜은, 남일우', 3  ,'PG','ROMANCE',NOW()),
         (58, '그 시절, 우리가 좋아했던 소녀','/static/images/dummy/when_we_like_a_girl/thumbnail.png','videolocation/when_we_like_a_girl/when_we_like_a_girl.mpd','/static/images/dummy/when_we_like_a_girl/main-photo.webp','/static/images/dummy/when_we_like_a_girl/poster-photo.png','/static/images/dummy/when_we_like_a_girl/text-photo.png','주다바오','학교 대표 얼간이 커징텅과 친구들은 최고의 모범생 션자이를 좋아한다. 수업 도중 사고를 친 커징텅은 션자이의 특별 감시를 받게 되고 둘은 점점 가까워진다. 션자이에 대한 마음이 커진 커징텅은 자신만의 방식으로 고백을 하지만 션자이는 대답하지 않는다. 그렇게 15년이 지나고, 두 사람은 다시 만나게 되는데… 그 때 너도 나와 같은 마음이었을까?','달콤 쌉싸름, 허당 매력, 로맨틱','1시간 47분','2011','주다바오','가진동, 진연희, 하오사오원, 좡하오취안, 차이창셴, 옌성위, 완완',  3 ,'PG','ROMANCE',NOW()),
         (96, '싱글 인 서울','/static/images/dummy/single_in_seoul/thumbnail.webp','videolocation/single_in_seoul/single_in_seoul.mpd','/static/images/dummy/single_in_seoul/main-photo.webp','/static/images/dummy/single_in_seoul/poster-photo.webp','/static/images/dummy/single_in_seoul/text-photo.webp','박범수','싱글 라이프에 관한 책을 함께 만들게 된 두 사람. 혼자 사는 삶에 대해 정반대의 가치관을 가진 그들이 어느덧 서로에게 끌리기 시작한다.','로맨틱','1시간 43분','2022','이지민','이동욱, 임수정, 이솜, 장현성, 김지영, 이미도, 이상이, 지이수', 3  ,'PG','ROMANCE',NOW()),
         (103, '선과 악의 학교','/static/images/dummy/school_of_evil_good/thumbnail.png','videolocation/school_of_evil_good/school_of_evil_good.mpd','/static/images/dummy/school_of_evil_good/main-photo.png','/static/images/dummy/school_of_evil_good/poster-photo.png','/static/images/dummy/school_of_evil_good/text-photo.png','폴 피그','절친 소피와 아가사는 어느 날 신비한 존재에 의해 마법 학교로 휩쓸려 간다. 이 학교는 미래의 동화 속 영웅과 악당을 키워내는 곳. 운명을 알 길 없는 이곳에서 두 친구의 우정은 계속될 수 있을까.','유쾌 발랄, 상상의 나래, 흥미진진','2시간 29분','2022','데이비드 매기, 폴 피그','소피아 앤 카루소, 소피아 와일리, 샤를리즈 테론, 케리 워싱턴, 로런스 피시번, 양자경, 제이미 플래터스, 킷 영, 피터 세러피너위치, 롭 딜레이니, 마크 헵, 패티 루폰, 레이철 블룸', 2 ,'PG','FANTASY',NOW()),
         (84, '조선명탐정','/static/images/dummy/chosun_detector/thumbnail.png','videolocation/chosun_detector/chosun_detector.mpd','/static/images/dummy/chosun_detector/main-photo.webp','/static/images/dummy/chosun_detector/poster-photo.png','/static/images/dummy/chosun_detector/text-photo.png','김석윤','목에 난 이빨 자국, 불탄 시체... 흡혈괴마 소행으로 의심되는 괴사건을 맡은 조선 명탐정. 그런데 수사하는 곳마다 의문의 여인이 나타난다. 그녀와 함께 찾은 단서, 시신에 꽂힌 화살촉의 글자가 수상한데. 범인은 뭔가를 알리고 있다!','긴장감 넘치는, 흥미진진','1시간 59분','2018','이남규','김명민, 오달수, 김지원, 김범, 박근형, 우현, 이민기, 안내상, 현우', 2  ,'PG','FANTASY',NOW()),
         (72, '살인의 추억','/static/images/dummy/memories_murder/thumbnail.webp','videolocation/memories_murder/memories_murder.mpd','/static/images/dummy/memories_murder/main-photo.webp','/static/images/dummy/memories_murder/poster-photo.webp','/static/images/dummy/memories_murder/text-photo.webp','봉준호','80년대 화성에서 연쇄 살인이 발생한다. 생소한 사건인 데다 과학 수사도 미진한 시절, 육감과 추리력만 믿고 발로 뛰는 형사들. 미치도록 잡고 싶은 범인은 지금 어디 있는가.','긴장감 넘치는','2시간 10분','2003','봉준호, 심성보','송강호, 김상경, 김뢰하',  3 ,'PG','THRILLER',NOW()),
         (71, '트와일라잇','/static/images/dummy/twilight/thumbnail.png','videolocation/twilight/twilight.mpd','/static/images/dummy/twilight/main-photo.png','/static/images/dummy/twilight/poster-photo.png','/static/images/dummy/twilight/text-photo.png','캐서린 하드윅','17세의 평범한 고등학생 소녀 ‘벨라’는 집안 사정으로 워싱턴 주 포크스에 있는 아빠의 집으로 이사를 온다. 전학 첫날, ‘벨라’는 냉담하지만 자신을 무장 해제시킬 정도로 잘생긴 ‘에드워드’와 마주치고, 전율과 두려움 넘치는 인생의 전환을 맞이한다. ‘에드워드’와 돌이킬 수 없는 사랑에 빠져든 ‘벨라’. 하지만 ‘에드워드’와 그의 가족이 뱀파이어 일족이라는 사실을 알게 되고, 예기치 못한 운명에 빠져든다.','불길한, 긴장감 넘치는, 로맨틱','2시간 1분','2008','멀리사 로젠버그','크리스틴 스튜어트, 로버트 패틴슨, 빌리 버크, 피터 파시넬리, 엘리자베스 리저, 캠 지간데이, 애슐리 그린, 애나 켄드릭, 니키 리드, 테일러 로트너, 잭슨 라스본, 켈런 러츠',  1 ,'PG','FANTASY',NOW());
INSERT INTO content_tb (
    real_play_time, title, thumbnail, video_path, main_photo, poster_photo, text_photo,director, introduction,
    characteristic, play_time, product_year, writers, actors, view_count, rate, genre, created_at
) VALUES (157, '마스크','/static/images/dummy/mask/thumbnail.png','videolocation/mask/mask.mpd','/static/images/dummy/mask/main-photo.png','/static/images/dummy/mask/poster-photo.png','/static/images/dummy/mask/text-photo.png','척 러셀','우연히 고대의 마스크를 발견한 평범한 은행원. 호기심에 써보자 초인적 힘이 솟구치고 무한대의 능력이 발휘된다. 감히 넘보지 못했던 여성의 마음도 사로잡게 되지만 짜릿한 경험도 잠시. 마스크가 초래한 소동이 걷잡을 수 없이 번진다.','익살스러운, 허당 매력','1시간 41분','1994','마이크 워브, 마이클 팰런, 마크 베어하이든','짐 캐리, 카메론 디아즈, 피터 리거트, 피터 그린, 에이미 야스벡, 리처드 제니, 오레스테스 마타세나, 팀 배글리, 낸시 피시, 조니 윌리엄스',  3 ,'PG','FANTASY',NOW()),
         (149, '쥬만지','/static/images/dummy/jumanji/thumnail.png','videolocation/jumanji/jumanji.mpd','/static/images/dummy/jumanji/main-photo.webp','/static/images/dummy/jumanji/poster-photo.png','/static/images/dummy/jumanji/text-photo.png','제이크 캐스단','말썽을 피운 벌로 학교 창고를 청소하게 된 4명의 고등학생. 우연히 발견한 비디오 게임을 시작하는 순간, 게임 속 세계로 빨려 들어간다. 각자 선택한 아바타가 되어 임무를 수행해야 하는 네 사람. 과연 무사히 게임을 끝낼 수 있을까?','아드레날린 폭발, 상상의 나래, 흥미진진','1시간 59분','2017','크리스 매케나. 에릭 서머스, 스콧 로젠버그, 제프 핑크너','드웨인 존슨, 케빈 하트, 잭 블랙, 캐런 길런, 라이스 다비, 바비 캐너발리, 닉 조나스, 앨릭스 울프, 서대리어스 블레인, 매디슨 아이스먼, 모건 터너, 메리베스 먼로, 미시 파일, 마크 에번 잭슨',  2 ,'PG','FANTASY',NOW()),
         (151, '러시아워','/static/images/dummy/rush_hour/thumbnail.webp','videolocation/rush_hour/rush_hour.mpd','/static/images/dummy/rush_hour/main-photo.webp','/static/images/dummy/rush_hour/poster-photo.jpg','/static/images/dummy/rush_hour/text-photo.webp','브렛 래트너','한 홍콩 형사가 유괴 사건 조사를 위해 로스앤젤레스로 소환되자, FBI는 그를 사건에서 떼어놓고자 사고뭉치 로스앤젤레스 경찰을 그의 파트너로 지정한다.','흥미진진','1시간 37분','1998','짐 카우프, 로스 라마','성룡, 크리스 터커, 톰 윌킨슨, 엘리자베스 페냐, 크리스 펜, 필립 베이커 홀, 마크 롤스톤, 타이 마, 렉스 린, 켄 렁',2,'PG','ACTION',NOW()),
         (29, '나는 신이다: 신이 배신한 사람들','/static/images/dummy/i_am_god/thumbnail.jpg','videolocation/i_am_god/i_am_god.mpd','/static/images/dummy/i_am_god/main-photo.webp','/static/images/dummy/i_am_god/poster-photo.webp','/static/images/dummy/i_am_god/text-photo.webp','조성현','대한민국의 네 교주와 그 신도들을 둘러싼 충격적인 이야기를 추적한 범죄 실화 다큐시리즈. 조성현이 연출을 맡았다.','진실을 찾아','1시간 xx분','2023','조성현',null,3,'R','DOCUMENTARY',NOW()),
         (29, '고양이는 왜 고양이일까?','/static/images/dummy/why_cat/thumbnail.jpg','videolocation/why_cat/why_cat.mpd','/static/images/dummy/why_cat/main-photo.webp','/static/images/dummy/why_cat/poster-photo.jpg','/static/images/dummy/why_cat/text-photo.webp','앤디 미첼','고양이에 관한 즐겁고 유익한 다큐멘터리. 에미상 수상자 앤디 미첼이 고양이와 인간 사이의 매적인 관계를 절묘하게 포착했다.','눈을 뗄 수 없는, 동물은 우리의 친구','1시간 7분','2022','앤디 미첼',null,2,'ALL','DOCUMENTARY',NOW()),
         (56, '오펜하이머','/static/images/dummy/oppenheimer/thumbnail.png.', 'videolocation/oppenheimer/oppenheimer.mpd', '/static/images/dummy/oppenheimer/main-photo.png', '/static/images/dummy/oppenheimer/poster.webp', '/static/images/dummy/oppenheimer/text-photo.png', '크리스토퍼 놀란', '큰 포부를 품은 물리학자가 위대한 과학적 발견과 그로 인한 참혹한 결과 사이에서 갈등에 휩싸인다. 지적인 매력의 시대극 영화.','달콤 쌉싸름', '3시간', '2023', '크리스토퍼 놀란', '킬리언 머피, 로버트 다우니 주니어, 에밀리 블런트, 맷 데이먼, 제이슨 클라크, 플로렌스 퓨, 케네스 브래너, 게리 올드먼, 라미 말렉',10, 'PG', 'THRILLER',NOW());




-- 생성 순서 : 5번, 결재 내역 정보 더미
INSERT INTO purchase_history_tb (card_info_id, user_id, description, service_period, paymethod, amount, created_at)
VALUES
    -- user_id 2
    (1, 2, '스트리밍 서비스', '2024-06-14~2024-07-13', 'CREDITCARD', 13500, '2024-04-14 12:00:00'),
    (2, 2, '스트리밍 서비스', '2024-05-14~2024-06-13', 'CREDITCARD', 13500, '2024-05-14 10:30:00'),
    -- user_id 3
    (3, 3, '스트리밍 서비스', '2024-06-14~2024-07-13', 'KAKAOPAY', 13500, '2024-05-14 15:45:00'),
    (3, 3, '스트리밍 서비스', '2024-05-14~2024-06-13', 'KAKAOPAY', 13500, '2024-06-14 09:00:00'),
    -- user_id 4
    (4, 4, '스트리밍 서비스', '2024-06-14~2024-07-13', 'CREDITCARD', 13500, '2024-06-01 09:15:00'),
    (4, 4, '스트리밍 서비스', '2024-05-14~2024-06-13', 'CREDITCARD', 13500, '2024-07-01 14:20:00'),
    -- user_id 5
    (5, 5, '스트리밍 서비스', '2024-06-14~2024-07-13', 'KAKAOPAY', 13500, '2024-06-01 08:30:00'),
    (5, 5, '스트리밍 서비스', '2024-05-14~2024-06-13', 'KAKAOPAY', 13500, '2024-07-01 11:45:00');

INSERT INTO content_tb (
    real_play_time, title, thumbnail, video_path, main_photo, poster_photo, text_photo,director, introduction,
    characteristic, play_time, product_year, writers, actors, view_count, rate, genre, created_at
) VALUES
    (102, '스파이더맨 : 홈커밍','/static/images/movie/home_coming/thumbnail.webp', 'videolocation/home_coming/home_coming.mpd', '/static/images/movie/home_coming/main_photo.webp', '/static/images/movie/home_coming/poster_photo.jpg', '/static/images/movie/home_coming/text_photo.webp', '존 왓츠', '톰 홀랜드와 로버트 다우니 주니어가 출연하는 2017년 슈퍼 히어로 영화. 마블 코믹스의 대표적인 캐릭터를 바탕으로 만들었다.','달상상의 나라, 긴장감 넘치는, 흥미진진', '2시간 13분', '2017', '조너선 골드스타인, 존 프랜시스 데일리, 존 왓츠, 크리스토퍼 포드, 크리스 맥케나, 에릭 소머스', '톰 홀랜드, 마이클 키튼, 로버트 다우니 주니어, 머리사 토메이, 존 패브로, 케네스 챈트로, 젠데이아, 도널드 글로버, 제이컵 배틀론',10, 'PG', 'ACTION',NOW());

--- 생성 순서 : 6번, mylist 테이블
INSERT INTO mylist_tb (user_id, content_id, played_time, watch_or_fav, created_at)
VALUES
    (1, 1, 0, 'WATCHING', NOW()),
--     (1, 2, 0, 'WATCHING', NOW()),
--     (1, 3, 0, 'FAVORITE', NOW()),
    (2, 1, 0, 'FAVORITE', NOW()),
    (2, 47, 50, 'WATCHING', NOW()),
    (2, 30, 20, 'WATCHING', NOW()),
    (2, 20, 20, 'WATCHING', NOW()),
    (2, 47, 0, 'FAVORITE', NOW()),
    (2, 35, 0, 'FAVORITE', NOW());




--- 생성 순서 : 7번, 환불요청 테이블
INSERT INTO refund_tb (user_id, reason, status, purchased_date, refuse_message, created_at) VALUES
    (2, 'CONTENT_ISSUE', 'ACCEPTED','2024-03-02','', NOW()),
    (3, 'TECHNICAL_ISSUE', 'REJECTED','2024-03-02', '죄송합니다.', NOW()),
    (4, 'SUBSCRIPTION_ISSUE', 'REJECTED','2024-03-02', '죄송합니다', NOW()),
    (5, 'PAYMENT_ISSUE', 'PENDING','2024-03-02','', NOW());

--- 생성 순서 : 8번, watchingHistory 테이블
INSERT INTO watching_history_tb (user_id, content_id, played_time, created_at)
VALUES
    (2, 1, 50, NOW()),
    (2, 10, 120, NOW()),
    (2, 20, 50, NOW()),
    (2, 30, 100, NOW()),
    (2, 40, 120, NOW());


