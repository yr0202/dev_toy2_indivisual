package com.fastcampus.toy2_7.dao;

import com.fastcampus.toy2_7.domain.NoticeDto;
import com.mysql.cj.protocol.x.Notice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// @Expected 쓰고 하드코딩 하지말고 get으로 얻어와라

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class NoticeDaoImplTest {
    @Autowired
    NoticeDaoImpl noticeDao;
    // select, delete, insert, update => @Test : 성공, 실패, 예외
    /*
    ### 작업 완료
    insert 성공 : 1개 넣고 count==1 -> 1개 추가 -> count==2
    insert 예외 : pk 중복으로 넣고 예외처리 하기, not null 칼럼에 null 넣고 count 확인
    insert 실패 : id==0 이면 데이터 삽입 x
    deleteAll 성공 : deleteAll -> count==0
    deleteById 성공 : 데이터 1개 삽입 -> count==1 -> deleteById -> count==0
    deleteById 실패 : 찾으려는 id값이 테이블에 없을 때 count 값 일정
    selectAllTest 성공 : 데이터 20개 list에 삽입 -> list.size로 확인 + 20개 중 랜덤으로 1개 확인 해보기
    selectOneById 성공 :
    selectOneById 실패 : 테이블에 없는 id값을 조회했을때 반환받는 Dto가 null
    update 성공 :
    update 실패 : 바꾸려는 필드를 찾을 수 없어서 업데이트 실패


    ### 작업 전
    *** 예외처리, 등록날짜 내림차순 정렬
    insert 예외 : not null 칼럼에 null 넣고 count 확인
    update 예외 : 예상 결과와 다른경우
    delete 2개 이상 같이 삭제

     */

//    @Before
//    public void setUp() throws Exception {
//        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
//        assertTrue(noticeDao.count() == 0); // 삭제 확인
//
//        List<NoticeDto> list = new ArrayList<>();
//
//        for (int i = 1; i <= 200; i++) { // 데이터 20개 넣어보기
//            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
//            noticeDao.insert(noticeDto);
//            list.add(noticeDto);
//        }
//        assertTrue(noticeDao.count()==200); // 총 삽입한 데이터 20개 확인
//    }

    @Test // 모든 데이터 검색
    public void selectAllTest()throws Exception{
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        List<NoticeDto> list = new ArrayList<>();

        for (int i = 1; i <= 200; i++) { // 데이터 20개 넣어보기
            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
            noticeDao.insert(noticeDto);
            list.add(noticeDto);
        }

        List<NoticeDto> dbList = noticeDao.selectAll();

        int num = 150; // 확인할 noticeID
        assertEquals(list.get(num).getNoticeID(),dbList.get(num).getNoticeID());
        assertEquals(list.get(num).getNoticeTitle(),dbList.get(num).getNoticeTitle());
    }

    @Test // 필드 1개 검색 테스트
    public void selectOneById성공Test() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        List<NoticeDto> list = new ArrayList<>();

        for (int i = 1; i <= 200; i++) {
            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
            noticeDao.insert(noticeDto);
            list.add(noticeDto);
        }

        int searchId = 15; // 찾고싶은 noticeId

        NoticeDto noticeDto = noticeDao.selectOne(searchId); // 검색할 필드를 noticeDto에 담음
        assertNotNull(searchId); // Dto에 잘 담겼는지 확인

        assertEquals(searchId, noticeDto.getNoticeID()); // noticeDto 객체에서 NoticeID에 담긴 값이 같은지 확인
        assertTrue(noticeDto.getNoticeTitle().equals("title" + searchId)); // noticeDto의 NoticeTitle의 값이 검증
    }

    @Test // 필드 1개 검색 테스트
    public void selectOneById실패Test() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        List<NoticeDto> list = new ArrayList<>();

        for (int i = 1; i <= 200; i++) {
            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
            noticeDao.insert(noticeDto);
            list.add(noticeDto);
        }
        assertTrue(noticeDao.count()==200); //전체 필드 개수가 동일하게 담겼는지 확인

        List<NoticeDto> dbList = noticeDao.selectAll();

        int searchId = 9999; // noticeID로 필드 검색 없다는 보장이 없다

        for(NoticeDto noticeDto : dbList){
            if(noticeDto.getNoticeID() == searchId)
                fail("테스트실패");
        }

    }
    @Test // 전체 삭제
    public void deleteAllTest() throws Exception {
        noticeDao.deleteAll(); // 전체 삭제 시도
        assertTrue(noticeDao.count() ==0); // 전체 삭제가 됐는지 개수로 확인
    }

    @Test // 특정 필드 1개 삭제
    public void deleteById성공Test() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        List<NoticeDto> list = new ArrayList<>();

        for (int i = 1; i <= 200; i++) { // 데이터 20개 넣어보기
            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
            noticeDao.insert(noticeDto);
            list.add(noticeDto);
        }
        assertTrue(noticeDao.count()==200); // 총 삽입한 데이터 20개 확인

        int deleteNoticeID = 15; // deleteNoticeID인 필드 삭제

        noticeDao.deleteById(deleteNoticeID);
        List<NoticeDto> dbList = noticeDao.selectAll();
        assertTrue(dbList.size()==199);

        for(NoticeDto noticeDto : dbList){
            if (noticeDto.getNoticeID()==deleteNoticeID)
                fail("삭제 테스트 실패");
        }
    }
    @Test
    public void deleteById실패Test() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        List<NoticeDto> list = new ArrayList<>();

        for (int i = 1; i <= 200; i++) { // 데이터 20개 넣어보기
            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
            noticeDao.insert(noticeDto);
            list.add(noticeDto);
        }
        assertTrue(noticeDao.count()==200); // 총 삽입한 데이터 20개 확인

        int searchNoticeID = 9999; // noticeID가 테이블에 없는 필드 삭제

        noticeDao.deleteById(searchNoticeID); // 삭제 시도

        assertTrue(noticeDao.count()==200); // 데이터 삭제가 안됐으므로 20개 그대로
    }

    @Test
    public void insertSimpleTest() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        NoticeDto noticeDto = new NoticeDto(1,"test1","content1"); // 추가할 객체(데이터) 생성
        noticeDao.insert(noticeDto); // 삽입 시도
        assertTrue(noticeDao.count()==1); // 개수 확인

        NoticeDto noticeDto2 = new NoticeDto(2,"test2","content2"); // 두번째 객체 생성
        noticeDao.insert(noticeDto2); // 삽입 시도
        assertTrue(noticeDao.count()==2); // 개수 확인
    }

    // deleteAll -> count==0 확인 -> insert -> 제대로 들어갔는지 insert 중 1개 select로 확인
    @Test // 삽입 성공 테스트
    public void insertSuccessTest() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        List<NoticeDto> list = new ArrayList<>();

        for (int i = 1; i <= 200; i++) { // 데이터 20개 넣어보기
            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
            noticeDao.insert(noticeDto);
            list.add(noticeDto);
        }
        assertTrue(noticeDao.count()==200); // 총 삽입한 데이터 20개 확인


        int id = 1; // 20개 데이터 중 제대로 insert 됐는지 1개만 확인
        NoticeDto noticeDto = noticeDao.selectOne(id);
        assertTrue(noticeDto.getNoticeTitle().equals("title"+id));
    }

//    // 의미없는 테스트 코드
//    @Test // 테스트 데이터 여러개 넣을려고 만든 테스트
//    public void insertDataTest() throws Exception {
//        for (int i = 1; i <= 280; i++) {
//            NoticeDto noticeDto = new NoticeDto( "title" + i, "content" + i, "user" + i, LocalDateTime.now(), "n");
//            noticeDao.insertAI(noticeDto);
//        }
//    }

    // 삽입 실패
    @Test
    public void insertFailTest() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        NoticeDto noticeDto = new NoticeDto(); // Dto 객체 생성해서 id, title, content 값 넣기
        noticeDto.setNoticeID(0);
        noticeDto.setNoticeTitle("title");
        noticeDto.setNoticeContent("content");

        if(noticeDto.getNoticeID() != 0){ // 가정 : id가 0이면 삽입 x
            noticeDao.insert(noticeDto);
        }
        assertTrue(noticeDao.count()==0); // 삽입 실패
    }

    // insert 예외 : noticeID 중복으로 넣어보기 , 길이 안맞는거
    @Test
    public void insertExceptionTest() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count()==0); // 삭제 검증

        // 첫 번째 데이터 삽입 - 성공해야 함
        NoticeDto noticeDto1 = new NoticeDto(1, "title1", "content1", "user1", LocalDateTime.now());
        assertTrue(noticeDao.insert(noticeDto1) == 1); // 첫 번째 삽입 성공 확인

        // 동일한 noticeID로 두 번째 데이터 삽입 - 실패해야 함 (중복 키 에러)
        NoticeDto noticeDto2 = new NoticeDto(1, "title2", "content2", "user2", LocalDateTime.now());
        try{
            noticeDao.insert(noticeDto2); // 삽입 시도 -> 예외가 발생해서 fail()을 건너 뛰고 catch문이 실행돼야함
            fail("테스트 실패"); // 삽입이 되면(예외 발생 안하면) "테스트 실패"
        }catch (Exception e){
            assertTrue(e instanceof org.springframework.dao.DuplicateKeyException);
        }
        assertTrue(noticeDao.count()==1); // 삽입 실패 -> count는 1이어야함
    }

    @Test
    /* 수정 테스트 : deleteAll -> count==0 -> insert 2개-> count==2 ->(sleep(2000))-> update -> select */
    /* update 수정테스트(수정자/수정일시) 변경 */
    public void update성공Test() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        // 첫번째 필드 삽입
        NoticeDto noticeDto1 = new NoticeDto(1,"testTitle","testContent"); // 데이터를 담을 Dto객체를 생성함
        LocalDateTime regDate1 = LocalDateTime.now(); // 삽입 시 등록 날짜
        noticeDto1.setRegDate(regDate1);
        noticeDao.insert(noticeDto1); // 필드 삽입
        assertTrue(noticeDao.count() == 1); // 필드 삽입 성공 검증

        // 두번째 필드 삽입
        NoticeDto noticeDto2 = new NoticeDto(2,"testTitle2","testContent2"); // 데이터를 담을 Dto객체를 생성함
        LocalDateTime regDate2 = LocalDateTime.now(); // 삽입 시 등록 날짜
        noticeDto2.setRegDate(regDate2);
        noticeDao.insert(noticeDto2); // 필드 삽입
        assertTrue(noticeDao.count() == 2); // 필드 삽입 성공 검증

        // 잠시 대기 (업데이트와 비교할 시간 간격을 두기 위해)
        Thread.sleep(2000); // 2초 대기

        // 첫번째 필드 수정
        noticeDto1.setNoticeTitle("updatedTitle");
        noticeDto1.setNoticeContent("updatedContent");
        assertTrue(noticeDao.update(noticeDto1) == 1); // 업데이트 성공 검증

        // 업데이트된 데이터 확인
        NoticeDto updatedNotice = noticeDao.selectOne(noticeDto1.getNoticeID());
        assertEquals("updatedTitle", updatedNotice.getNoticeTitle());
        assertEquals("updatedContent", updatedNotice.getNoticeContent());
        assertNotNull(updatedNotice.getModDate()); // 수정 날짜가 업데이트된 것을 확인
    }

    @Test
    public void update성공Test2() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        List<NoticeDto> list = new ArrayList<>();

        for (int i = 1; i <= 200; i++) { // 데이터 20개 넣어보기
            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
            noticeDao.insert(noticeDto);
            list.add(noticeDto);
        }
        assertTrue(noticeDao.count()==200); // 총 삽입한 데이터 20개 확인

        // 잠시 대기 (업데이트와 비교할 시간 간격을 두기 위해)
        Thread.sleep(2000); // 2초 대기

        // noticeID가 random인 필드를 수정
        int random = 5;
        NoticeDto randomNoticeDto = noticeDao.selectOne(random); // noticeID 5인 필드 선택
        randomNoticeDto.setNoticeTitle("updatedTitle");
        randomNoticeDto.setNoticeContent("updatedContent");
        assertTrue(noticeDao.update(randomNoticeDto) == 1); // 업데이트 성공 검증

        // 업데이트된 데이터 확인
        NoticeDto updatedNotice = noticeDao.selectOne(random); // noticeID 5인 필드 다시 선택
        assertEquals("updatedTitle", updatedNotice.getNoticeTitle());
        assertEquals("updatedContent", updatedNotice.getNoticeContent());
        assertNotNull(updatedNotice.getModDate()); // 수정 날짜가 업데이트된 것을 확인
    }

    @Test(expected = NullPointerException.class)
    /*수정 실패 : insert 넣고 확인 -> 없는 id값을 수정 시도 -> 값 변경 확인  */
    // 널포인트 이셉션
    public void update예외Test() throws Exception {
        noticeDao.deleteAll(); // 전처리 과정:모든 데이터 삭제
        assertTrue(noticeDao.count() == 0); // 삭제 확인

        List<NoticeDto> list = new ArrayList<>();

        for (int i = 1; i <= 200; i++) { // 데이터 20개 넣어보기
            NoticeDto noticeDto = new NoticeDto(i, "title" + i, "content" + i);
            noticeDao.insert(noticeDto);
            list.add(noticeDto);
        }
        assertTrue(noticeDao.count()==200); // 총 삽입한 데이터 20개 확인

        // noticeID가 random인 필드를 수정
        int random = 9999;
//        assertNull(noticeDao.selectOne(random));

        NoticeDto randomNoticeDto = noticeDao.selectOne(random); // NullPointerException 발생 가능
        randomNoticeDto.setNoticeTitle("updatedTitle");
        randomNoticeDto.setNoticeContent("updatedContent");
        noticeDao.update(randomNoticeDto); // 업데이트 시도
    }

    @Test
    // dao 객체 존재 여부 테스트
    public void nullTest() {
        assertTrue(noticeDao!=null);
        System.out.println("noticeDao = " + noticeDao);
    }

    @Test
    public void displayFlagsN(){
//        assertTrue();
    }

//    List<NoticeDto> makeNoticeList() {
//        List<NoticeDto> list = new ArrayList<NoticeDto>();
//        for(int i=1;i<=20;i++){
//            NoticeDto noticeDto = new NoticeDto("testTitle"+i, "testContent"+i);
//            list.add(noticeDto);
//        }
//        return list;
//    }

}