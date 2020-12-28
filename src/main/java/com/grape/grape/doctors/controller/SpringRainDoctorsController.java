package com.grape.grape.doctors.controller;

import com.grape.grape.doctors.service.SpringRainDoctorsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * @ClassName: SpringRainDoctors
 * @Description: 春雨医生H5对接
 * @Author Chenjun
 * @Date 2020/12/26 11:09
 * @Version V1.0
 */
@RestController
@RequestMapping("/cj/doctor")
public class SpringRainDoctorsController {

    @Resource
    private SpringRainDoctorsService springRainDoctorsService;

    @GetMapping("/success")
    public ModelAndView getRainH5Url(){
        String requestUrl = springRainDoctorsService.getBusinessH5();
        return  new ModelAndView(new RedirectView(requestUrl));
    }


}
