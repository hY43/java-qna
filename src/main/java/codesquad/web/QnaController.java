package codesquad.web;

import codesquad.UnAuthenticationException;
import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.dto.UserDto;
import codesquad.security.LoginUser;
import codesquad.service.QnaService;
import codesquad.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/qnas")
public class QnaController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "qnaService")
    private QnaService qnaService;

    @GetMapping("/form")
    public String form() {
        return "/qna/form";
    }

}
