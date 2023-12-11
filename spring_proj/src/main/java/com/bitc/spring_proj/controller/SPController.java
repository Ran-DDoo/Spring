package com.bitc.spring_proj.controller;

import com.bitc.spring_proj.dto.QnaDTO;
import com.bitc.spring_proj.dto.UserDTO;
import com.bitc.spring_proj.service.QnaService;
import com.bitc.spring_proj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/Proj")
public class SPController {

    @Autowired
    private UserService userService;

    @Autowired
    private QnaService qnaService;

    @RequestMapping("/festival/pj_main")
    public String pj_main() throws Exception {
        return "festival/pj_main";
    }

    @GetMapping ("/Mypage/mypage")
    public ModelAndView mypage(HttpServletRequest req) throws Exception{
        ModelAndView mv = new ModelAndView("Mypage/mypage");

        HttpSession session = req.getSession();
        // 세션에 저장된 내용 가져오기
        String uId = (String) session.getAttribute("uId");
        String uName = (String) session.getAttribute("uName");

        if (uId == null || uName == null) {
            // null일시 로그인페이지로이동
            mv.setViewName("redirect:/login.login");
        }
        // mv.addObject(회원 정보);
        mv.addObject("uId", uId);
        mv.addObject("uName", uName);

        // 서비스를 사용하여 mapper로 해당 사용자의 게시물 목록 가져오기
        // mv.addObject(qna목록);
        // mv.addObject(review목록);


        return mv;
    }

    @GetMapping("/festival/f_detail")
    public String f_detail() throws Exception {
        return "festival/f_detail";
    }

    @GetMapping("/member/membership")
    public String membership() throws Exception {
        return "member/membership";
    }

    @PostMapping(value = "/member/membership")
    public String memberInsertProcess(UserDTO user, HttpServletResponse res) throws Exception {
        res.setContentType("text/html; charset=UTF-8");
        String url = "";
        String userId = user.getUId();
        String userPw = user.getUPw();
        String userName = user.getUName();

        if ((userId == "")|| (userPw == "")||(userName == "")){
            if (userId == ""){
                PrintWriter out = res.getWriter();
                out.println("<script>alert('이메일을 입력하세요'); history.back();</script>");
                out.flush();
            } else if (userPw == "") {
                PrintWriter out = res.getWriter();
                out.println("<script>alert('비밀번호를 입력하세요'); history.back();</script>");
                out.flush();
            } else if (userName == "") {
                PrintWriter out = res.getWriter();
                out.println("<script>alert('이름을 입력하세요'); history.back();</script>");
                out.flush();
            }
        }else {
            int cnt = userService.cntUser(user);

            if (cnt == 0) {
                userService.insertUser(user);
                url = "redirect:/login/login";
            } else {
                PrintWriter out = res.getWriter();
                out.println("<script>alert('이미있는 이메일 정보입니다..'); location.href = '/member/membership';</script>");
                out.flush();
            }
        }

        return url;
    }

    @GetMapping("/login/login")
    public String login() throws Exception {
        return "login/login";
    }

    @RequestMapping(value = "/login/loginProcess", method = RequestMethod.POST)
    public String loginProcess(@RequestParam("uId")String uId, @RequestParam("uPw")String uPw, HttpServletRequest req) throws Exception{
        int result = userService.isUserInfo(uId, uPw);

        if(result == 1){
            UserDTO user = userService.getUserInfo(uId);
            HttpSession session = req.getSession();
            session.setAttribute("uId", user.getUId());
            session.setAttribute("uName", user.getUName());
            session.setMaxInactiveInterval(60);

            return "/Proj/festival/pj_main";
        }
        else{
            return "login/login";
        }
    }


    @RequestMapping("/mypage.do")
    public ModelAndView boardDetail(@RequestParam("qcreateid") String qcreateid) throws Exception {
        ModelAndView mv = new ModelAndView("Mypage/mypage");

        List<QnaDTO> qnaList = qnaService.userQna(qcreateid);
        mv.addObject("qnaList", qnaList);

        return mv;
    }



}
