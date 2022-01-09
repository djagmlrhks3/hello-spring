package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); /* Spring에서 model을 만들어 key-value 형태로 {data:hello!!} 넘겨줌 */
        return "hello"; // resources > templates > return에 적은 이름.html 파일을 찾음
        /* 스프링 부트 템플릿엔진 기본 viewName 매핑
           >>> resources:templates/ + {ViewName} + .html
        */
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // 이전과 달리 외부에서 파라미터를 가져옴
        model.addAttribute("name", name);
        return "hello-template";
    }
}
