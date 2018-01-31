package codesquad.web;

import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.dto.QuestionDto;
import codesquad.dto.UserDto;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import support.test.AcceptanceTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ApiQuestionAcceptanceTest extends AcceptanceTest {

    private QuestionDto createQuestionDto() {
        return new QuestionDto("testTitle1", "testContent1");
    }

    @Test
    public void 질문_보기() throws Exception {
        long id = 1;
        ResponseEntity<String> response = template().getForEntity(String.format("/api/question/%d", id), String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void 질문_등록() throws Exception {
        User loginUser = defaultUser();
        QuestionDto newQuestion = createQuestionDto();
        ResponseEntity<String> response = basicAuthTemplate(loginUser).postForEntity("/api/question/", newQuestion, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    public void 질문_수정_login() throws  Exception {

    }

    @Test
    public void 질문_삭제_login() throws  Exception {
        User loginUser = defaultUser();
        long id = 1;
        ResponseEntity<String> response = basicAuthTemplate(loginUser).postForEntity(String.format("/api/question/%d", id), id, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
    }
}
