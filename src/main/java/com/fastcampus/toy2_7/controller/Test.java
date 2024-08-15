package com.fastcampus.toy2_7.controller;

@Controller
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/notices")
    public String list(@RequestParam(value="page", defaultValue="1") int page, int pageSize, Model model) {
        int totalCnt = noticeService.getTotalCount();  // 전체 공지사항 수
        PageHandler ph = new PageHandler(totalCnt, page);
        Map map =

        List<Notice> noticeList = noticeService.getNotices(page, pageSize);  // 공지사항 목록을 가져옴

        model.addAttribute("ph", ph);
        model.addAttribute("noticeList", noticeList);
        return "notice/list";
    }
}

@Service
public class NoticeService {

    @Autowired
    private NoticeDAO noticeDAO;

    // 전체 공지사항 수를 가져오는 메서드
    public int getTotalCount() {
        return noticeDAO.countAll();
    }

    // 페이징 처리된 공지사항 목록을 가져오는 메서드
    public List<Notice> getNotices(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return noticeDAO.selectNotices(offset, pageSize);
    }
}

@Repository
public class NoticeDAO {

    @Autowired
    private SqlSession sqlSession;

    // 전체 공지사항 수를 계산하는 메서드
    public int countAll() {
        return sqlSession.selectOne("NoticeMapper.countAll");
    }

    // 특정 페이지에 해당하는 공지사항 목록을 가져오는 메서드
    public List<Notice> selectNotices(int offset, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return sqlSession.selectList("NoticeMapper.selectNotices", params);
    }
}

<mapper namespace="com.fastcampus.toy2_7.domain.NoticeMapper">

    <!-- 공지사항 전체 개수를 세는 쿼리 -->
    <select id="countAll" resultType="int">
SELECT COUNT(*) FROM notices
    </select>

    <!-- 특정 페이지에 해당하는 공지사항 목록을 가져오는 쿼리 -->
    <select id="selectNotices" parameterType="map" resultType="Notice">
SELECT *
FROM notices
ORDER BY id DESC
LIMIT #{offset}, #{pageSize}
    </select>

</mapper>