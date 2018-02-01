package codesquad.web;

import codesquad.domain.User;
import codesquad.dto.QuestionDto;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApiAnswerAcceptanceTest extends AcceptanceTest {

    private QuestionDto createQuestionDto() {
        return new QuestionDto("testTitle1", "testContent1");
    }

    @Test
    public void 답변_보기() throws Exception {
        
    }

    @Test
    public void 답변_등록() throws Exception {

    }

    @Test
    public void 답변_수정_login() throws  Exception {

    }

    @Test
    public void 답변_삭제_login() throws  Exception {

    }
}
