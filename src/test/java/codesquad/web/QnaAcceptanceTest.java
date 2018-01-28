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

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class QnaAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(UserAcceptanceTest.class);
    private HtmlFormDataBuilder htmlFormDataBuilder;

    @Test
    public void createForm() throws Exception {
        ResponseEntity<String> response = template().getForEntity("/qnas/form", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.debug("body : {}", response.getBody());
    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void list() throws Exception {

    }

    @Test
    public void updateForm_no_login() throws Exception {

    }

    @Test
    public void updateForm_login() throws Exception {

    }

    @Test
    public void update_no_login() throws Exception {

    }

    private ResponseEntity<String> update(TestRestTemplate template) throws Exception {
        
        return null;
    }

    @Test
    public void update() throws Exception {

    }
}
