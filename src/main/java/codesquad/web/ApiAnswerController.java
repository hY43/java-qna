package codesquad.web;

import codesquad.CannotDeleteException;
import codesquad.domain.Answer;
import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.dto.QuestionDto;
import codesquad.security.LoginUser;
import codesquad.service.QnaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/question/{id}/answer")
public class ApiAnswerController {
    @Resource(name = "qnaService")
    private QnaService qnaService;

    @PostMapping("/")
    public ResponseEntity<String> create(@LoginUser User loginUser, @RequestBody Answer answer, @PathVariable Long id) {
        Answer saveAnswer = qnaService.addAnswer(loginUser, id, answer.getContents());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(String.format("/api/question/%d/answer/", id) + saveAnswer.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping("{id}")
    public void update(@LoginUser User loginUser, @PathVariable long id, @RequestBody Answer answer) {
        //Answer updateAnswer = qnaService.updateAnswer();
    }

    @DeleteMapping("{id}")
    public void delete(@LoginUser User loginUser, @PathVariable long id) {

    }
}
