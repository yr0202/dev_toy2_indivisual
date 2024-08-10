package com.fastcampus.toy2_7.controller;

import com.fastcampus.toy2_7.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notices")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

}
