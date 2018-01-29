package codesquad.web;

import codesquad.domain.QuestionRepository;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.test.AcceptanceTest;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class QnaAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(UserAcceptanceTest.class);
    private HtmlFormDataBuilder htmlFormDataBuilder;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void questionList() throws Exception {
        ResponseEntity<String> response = template().getForEntity("/questions", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.debug("body : {}", response.getBody());
    }

    @Test
    public void showQuestion() throws Exception {
        long id = 1;
        ResponseEntity<String> response = template().getForEntity(String.format("/question/%d", id), String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertNotNull(questionRepository.findOne(id));
        log.debug("body : {}", response.getBody());
    }

    @Test
    public void createForm() throws Exception {
        ResponseEntity<String> response = template().getForEntity("/questions/form", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.debug("body : {}", response.getBody());
    }

    @Test
    public void create() throws Exception {
        User loginUser = defaultUser();
        htmlFormDataBuilder.addParameter("title", "테스트 게시글1");
        htmlFormDataBuilder.addParameter("contents", "테스트 내용1");

        HttpEntity<MultiValueMap<String, Object>> request = htmlFormDataBuilder.build();
        ResponseEntity<String> response = basicAuthTemplate(loginUser).postForEntity("/question", request, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
        assertThat(response.getHeaders().getLocation().getPath(), is("/questsion"));
    }
}
