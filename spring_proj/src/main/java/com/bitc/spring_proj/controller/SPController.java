package com.bitc.spring_proj.controller;

import com.bitc.spring_proj.dto.FestaItemDTO;
import com.bitc.spring_proj.dto.UserDTO;
import com.bitc.spring_proj.service.ProjService;
import com.bitc.spring_proj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/Proj")
public class SPController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjService projService;

    @Value("AUIh5hIEboiMc%2FZLcDpczV%2BCf7FPo1e8CHSo0kb8ejd5IWHsX7AhgdcUzzGUsZgzoBZIu0iN1eA82qvQq%2FbLlA%3D%3D")
    private String serviceMyKey;

    @RequestMapping("/festival/pj_main")
    public String pj_main() throws Exception {
        return "festival/pj_main";
    }

    @GetMapping("/festival/f_list")
    public String f_list() throws Exception {
        return "festival/f_list";
    }

    @GetMapping("/festival/f_detail")
    public String f_detail() throws Exception {
        return "festival/f_detail";
    }

    @ResponseBody
    @PostMapping("/festival/f_list")
    public Object FestaList(@RequestParam("pageNo") int pageNo, @RequestParam("numOfRows") int numOfRows) throws Exception{
        String url = "https://apis.data.go.kr/6260000/FestivalService/getFestivalKr";
        String optKey = "?serviceKey=";
        String opt1 = "&pageNo=";
        String opt2 = "&numOfRows=";
        String opt3 = "&resultType=JSON";
        String serviceUrl = url + optKey + serviceMyKey + opt1 + pageNo + opt2 + numOfRows + opt3;

        List<FestaItemDTO> festaList = projService.FestaList(serviceUrl);

        return festaList;
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



}
