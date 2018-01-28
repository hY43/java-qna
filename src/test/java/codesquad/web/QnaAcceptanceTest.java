package codesquad.web;

import codesquad.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Test
    public void list() throws Exception {
        ResponseEntity<String> response = template().getForEntity("/questions", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.debug("body : {}", response.getBody());
    }

    @Test
    public void createForm() throws Exception {
        ResponseEntity<String> response = template().getForEntity("/qnas/form", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.debug("body : {}", response.getBody());
    }

}
