package com.fastcampus.toy2_7.controller;

import com.fastcampus.toy2_7.domain.MemberDto;
import com.fastcampus.toy2_7.domain.NoticeDto;
import com.fastcampus.toy2_7.domain.PageHandler;
import com.fastcampus.toy2_7.domain.SearchCondition;
import com.fastcampus.toy2_7.service.MemberService;
import com.fastcampus.toy2_7.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

/*
1.전체 공지사항 목록 보기
3.공지사항 작성 - admin
4.공지사항 수정 - admin
5.공지사항 삭제 (1개, n개) - admin
6.공지사항 전체 삭제 - admin
공지글 보여줌 표시 여부 칼럼 추가하기
 */

@Controller
@RequestMapping("/notices")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @Autowired
    MemberService memberService;

    @GetMapping
    public String list(Model m, SearchCondition sc, HttpSession session, HttpServletRequest request) {
        // 세션에서 로그인된 사용자 ID를 가져옴
        String memberId = request.getSession().getId();
        System.out.println("memberId = " + memberId);
//        String memberId = (String) session.getAttribute("id");
        System.out.println("list controller -> session.getId value : "+ memberId);//admin

        // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        if (memberId == null) {
            return "redirect:/login/login";
        }

        try {
            // 로그인한 사용자 정보를 member 테이블에서 가져옴
            MemberDto loggedInMember = memberService.getMemberById(memberId);

            // 사용자 역할 확인 (isAdmin 값 확인)
            char isAdmin = loggedInMember.getIsAdmin();
            System.out.println("loggedInMember.getIsAdmin() = " + loggedInMember.getIsAdmin()); //y
            boolean isAdminRole = (isAdmin == 'Y');  // 관리자 여부를 확인

            m.addAttribute("role", isAdminRole ? "admin" : "user");  // role 추가  // 관리자 여부를 Model에 추가

            // 총 검색 결과 수 (검색 조건에 따라 다름)
            int totalCnt = noticeService.getSearchResultCnt(sc);
            System.out.println("totalCnt: " + totalCnt);
            m.addAttribute("totalCnt", totalCnt);

            // 페이지 처리
            PageHandler pageHandler = new PageHandler(totalCnt, sc.getPage());
            m.addAttribute("ph", pageHandler);

            List<NoticeDto> noticeList;

            if (isAdminRole) {
                // 관리자는 모든 게시물을 볼 수 있음
                noticeList = noticeService.getSearchResultPage(sc);
                System.out.println("공지사항 목록: " + noticeList);  // noticeList 내용 출력
            } else {
                // 일반 사용자는 표시 가능한 게시물만 볼 수 있음
                noticeList = noticeService.getVisibleNoticesForUser(sc);
            }

            System.out.println("공지사항 목록: " + noticeList);  // 디버깅용 출력

            // 공지사항 목록을 Model에 추가
            m.addAttribute("notices", noticeList);
            m.addAttribute("userId", loggedInMember.getUserID());

//            // 오늘의 시작 시간을 추가 (특정한 비교나 표시 용도)
//            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
//            m.addAttribute("startOfToday", startOfToday.toEpochMilli());

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "notice/list";  // 공지사항 목록 화면으로 이동
    }


    // 기존에 사용하던 코드 에러뜨면 이걸로 다시 돌려보기
//    @GetMapping
//    public String getNoticelist( Model m, HttpSession session) throws Exception {
//        List<NoticeDto> noticeList;
//
//        // role이 admin인지 user인지 임의로 설정
//        session.setAttribute("role", "admin");
////        session.setAttribute("role", "user");
//
//        String role = (String) session.getAttribute("role");
//
//        if (role.equals("admin")) {
//            // 관리자는 모든 게시물을 볼 수 있음
//            noticeList = noticeService.findAll();
////            noticeList = noticeService.readAllNotices();
//            session.setAttribute("userId", "admin1");  // 사용자 ID 임의로 설정
//        } else {
//            noticeList = noticeService.readVisibleNotices();
//            session.setAttribute("userId", "user1");
//        }
//        session.setAttribute("role", role);
//
//        m.addAttribute("notices", noticeList);
//        m.addAttribute("userId", session.getAttribute("userId"));
//        m.addAttribute("role", session.getAttribute("role"));    // 사용자 권한을 설정
//
//        return "notice/list";
//    }


    //게시물 표시 여부 전체 선택 해제(null)일 경우 예외처리
    @PostMapping("/updateDisplayFlags")
    public String updateDisplayFlags(@RequestParam(value = "displayFlags", required = false) List<Integer> displayFlagIds) { // required = false : null 허용
        //예외처리 : 어떤 상황에서 어떤 페이지로 갈건지 정리
        noticeService.updateDisplayFlags(displayFlagIds);
        return "redirect:/notices";  // 수정 후 다시 목록으로 리다이렉트
    }

    // 2. 공지사항 상세 보기
    @GetMapping("/{id}")
    public String read(@PathVariable("id") int noticeID, Model m) {
        NoticeDto notice = noticeService.readNotice(noticeID);
        m.addAttribute("notice", notice);
        return "notice/detail";  // view 이름, notice/detail.html
    }

    // 3. 공지사항 작성 페이지로 이동
    @GetMapping("/new")
    public String createMove(NoticeDto noticeDto) {
//        noticeService.creatNotice(noticeDto);
        return "notice/create";
    }
    // 3. 공지사항 작성
    @PostMapping("/insertNotice")
    public String create(NoticeDto noticeDto,HttpSession session) {
        noticeDto.setRegId((String)session.getAttribute("userId"));
        noticeService.creatAutoNotice(noticeDto);
//        return "notice/create";
        return "redirect:/notices";  // 생성 후 목록 페이지로 리다이렉트
    }

    // 4. 공지사항 수정 페이지로 이동
    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") int noticeID, Model model) {
        NoticeDto notice = noticeService.readNotice(noticeID);
        model.addAttribute("notice", notice);
        return "notice/edit";  // view 이름, notice/edit.html
    }



    // 5. 공지사항 삭제 (단일)
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable ("id") int noticeID) {
        noticeService.deleteNotice(noticeID);
        return "redirect:/notices";
    }

    // 6. 공지사항 다중 삭제
    @PostMapping("/delete")
    public String deleteMultiple(@RequestParam("ids") List<Integer> noticeIDs) {
        noticeService.deleteNotices(noticeIDs);
        return "redirect:/notices";
    }

    // 7. 공지사항 전체 삭제
    @PostMapping("/deleteAll")
    public String deleteAll() {
        noticeService.deleteAllNotices();
        return "redirect:/notices";
    }

}
