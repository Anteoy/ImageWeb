package com.lawcall.web.image;

import com.lawcall.web.image.entity.UserInfo;
import com.lawcall.web.image.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private MappingJacksonJsonView mappingJacksonJsonView;

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 登陆系统
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

        String yonghm = request.getParameter("yonghm");
        String mim = request.getParameter("mim");
        Map<String, Object> map = new HashMap<String, Object>();

        try {

            UserInfo userInfo = loginMapper.hqyh(yonghm, mim);
            if (userInfo != null) {
                map.put("success", 1);
            } else {
                map.put("success", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(mappingJacksonJsonView, map);
    }

}
