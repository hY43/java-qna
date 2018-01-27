package codesquad.web;

import codesquad.UnAuthenticationException;
import codesquad.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.test.AcceptanceTest;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginAcceptanceTest extends AcceptanceTest{
    private static final Logger log = LoggerFactory.getLogger(UserAcceptanceTest.class);

    HttpHeaders headers;
    MultiValueMap<String, Object> params;

    @Before
    public void setup() {
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        params = new LinkedMultiValueMap<>();
    }

    @Test
    public void login_success(){

        params.add("userId", "javajigi");
        params.add("password", "test");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        ResponseEntity<String> response = template().postForEntity("/users/login", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
    }

    @Test
    public void login_failure(){

        params.add("userId", "javajigi");
        params.add("password", "test1");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        ResponseEntity<String> response = template().postForEntity("/users/login", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

}
