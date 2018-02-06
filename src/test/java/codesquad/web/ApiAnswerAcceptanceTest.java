package codesquad.web;

import codesquad.domain.Answer;
import codesquad.domain.User;
import codesquad.dto.QuestionDto;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApiAnswerAcceptanceTest extends AcceptanceTest {

    private Answer createAnswer(User loginUser){
        return new Answer(loginUser, "testContents");
    }

    @Test
    public void 답변_확인() throws Exception {
        long questionId = 1;
        long answerId = 1;
        ResponseEntity<String> response = template().getForEntity(String.format("/api/question/%d/answer/%d", questionId, answerId), String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void 답변_등록() throws Exception {
        long id = 1;
        User loginUser = defaultUser();
        Answer newAnswer = createAnswer(loginUser);

        ResponseEntity response = basicAuthTemplate(loginUser).postForEntity(String.format("/api/question/%d/answer/", id), newAnswer, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    public void 답변_수정_login() throws  Exception {
        long questionId = 1;
        long answerId = 1;
        User loginUser = defaultUser();
        Answer newAnswer = createAnswer(loginUser);
        basicAuthTemplate(loginUser).put(String.format("/api/question/%d/answer/%d", questionId, answerId),newAnswer, Answer.class);

        Answer updatedAnswer = template().getForObject(String.format("/api/question/%d/answer/%d", questionId, answerId), Answer.class);
        assertThat(updatedAnswer.getContents(), is(newAnswer.getContents()));
    }

    @Test
    public void 답변_삭제_login() throws  Exception {
        long questionId = 1;
        long answerId = 1;
        User loginUser = defaultUser();
        basicAuthTemplate(loginUser).delete(String.format("/api/question/%d/answer/%d", questionId, answerId));

        ResponseEntity<String> response = template().getForEntity(String.format("/api/question/%d/answer/%d", questionId, answerId), String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}
