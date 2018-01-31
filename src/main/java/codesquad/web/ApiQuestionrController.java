package codesquad.web;

import codesquad.CannotDeleteException;
import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.dto.QuestionDto;
import codesquad.dto.QuestionsDto;
import codesquad.dto.UserDto;
import codesquad.security.LoginUser;
import codesquad.service.QnaService;
import codesquad.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/question")
public class ApiQuestionrController {
    @Resource(name = "qnaService")
    private QnaService qnaService;

    @PostMapping("/")
    public ResponseEntity<String> create(@LoginUser User loginUser, @RequestBody QuestionDto questionDto) {
        Question savedQuestion = qnaService.create(loginUser, new Question(questionDto.getTitle(), questionDto.getContents()));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/question/" + savedQuestion.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public QuestionDto show(@PathVariable long id) {
        Question question = qnaService.findById(id);
        return question.toQuestionDto();
    }
    
    @PutMapping("{id}")
    public void update(@LoginUser User loginUser, @PathVariable long id, @Valid @RequestBody QuestionDto updatedQuestion) {
        qnaService.update(loginUser, id, new Question(updatedQuestion.getTitle(), updatedQuestion.getContents()));
    }

    @DeleteMapping("{id}")
    public void delete(@LoginUser User loginUser, @PathVariable long id) {
        try {
            qnaService.deleteQuestion(loginUser, id);
        } catch (CannotDeleteException e) {
            e.printStackTrace();
        }
    }
}
