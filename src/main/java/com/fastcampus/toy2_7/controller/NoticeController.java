package com.fastcampus.toy2_7.controller;

import com.fastcampus.toy2_7.domain.NoticeDto;
import com.fastcampus.toy2_7.domain.PageHandler;
import com.fastcampus.toy2_7.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notices")
/*
1.전체 공지사항 목록 보기
3.공지사항 작성 - admin
4.공지사항 수정 - admin
5.공지사항 삭제 (1개, n개) - admin
6.공지사항 전체 삭제 - admin

공지글 보여줌 표시 여부 칼럼 추가하기
 */
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    // 1. 전체 공지사항 목록 보기 (페이징 추가)
    @GetMapping
    public String getNoticeList(@RequestParam(value = "role", required = false, defaultValue = "admin") String role,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                Model m, HttpServletRequest request) throws Exception {
        List<NoticeDto> noticeList;

        HttpSession session = request.getSession();
//        String role = session.getAttribute("role"); //원래는 세션에서 role의 값을 가져와야함

        // 임의로 세션에 "userId" 값을 세팅함
        if (role.equals("admin")) {
            session.setAttribute("userId", "admin1");
        } else {
            session.setAttribute("userId", "user1");
        }
        // 임의로 세션에 "role" 값을 세팅함
        session.setAttribute("role", role);


        // role에 따라 볼 수 있는 목록 화면이 다름
        int totalCnt;
        if (role.equals("admin")) {
            // 관리자는 모든 게시물을 볼 수 있음
            totalCnt = noticeService.getTotalCount();
            noticeList = noticeService.getNotices(page, pageSize);
        } else {
            totalCnt = noticeService.getVisibleNoticeCount();
            noticeList = noticeService.getVisibleNotices(page, pageSize);
        }

        PageHandler ph = new PageHandler(totalCnt, page, pageSize);

        m.addAttribute("notices", noticeList);
        m.addAttribute("userId", session.getAttribute("userId"));
        m.addAttribute("role", session.getAttribute("role"));    // 사용자 권한을 설정
        m.addAttribute("ph", ph);

        return "notice/list";
    }

    // 세션 or dto 가져와서 값 확인하기...

    //게시물 표시 여부 전체 선택 해제(null)일 경우 예외처리
    @PostMapping("/updateDisplayFlags")
    public String updateDisplayFlags(@RequestParam(value = "displayFlags", required = false) List<Integer> displayFlagIds) { // required : null 허용
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
    public String create(NoticeDto noticeDto) {
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

//    // 4. 공지사항 수정 처리
//    @PostMapping("/edit/{id}")
//    public String update(@PathVariable("id") int noticeID, NoticeDto noticeDto) {
//        noticeDto.setNoticeID(noticeID);
//        noticeService.updateNotice(noticeDto);
//        return "redirect:/notices";  // 수정 후 목록 페이지로 리다이렉트
//    }

//    @PostMapping("/edit/{id}")
//    public String update(@PathVariable("id") int noticeID,
//                         @ModelAttribute NoticeDto noticeDto) {
//        noticeDto.setNoticeID(noticeID);
//        noticeService.updateNotice(noticeDto);
//        return "redirect:/notices";
//    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int noticeID,
                         @ModelAttribute NoticeDto noticeDto) {
        // Check if displayFlag is null, and set a default value if needed
        if (noticeDto.getDisplayFlag() == null) {
            noticeDto.setDisplayFlag("n");  // Default to 'n' if unchecked
        }
        noticeDto.setNoticeID(noticeID);
        noticeService.updateNotice(noticeDto);
        return "redirect:/notices";
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
