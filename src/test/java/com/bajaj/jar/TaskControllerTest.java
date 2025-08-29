package com.bajaj.jar;

import com.bajaj.jar.model.CreateTaskRequest;
import com.bajaj.jar.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate rest;

    @Test
    void healthAndCrud() {
        // health
        ResponseEntity<String> h = rest.getForEntity(url("/api/health"), String.class);
        assertThat(h.getStatusCode()).isEqualTo(HttpStatus.OK);

        // create
        CreateTaskRequest req = new CreateTaskRequest();
        req.setTitle("Write resume");
        ResponseEntity<Task> created = rest.postForEntity(url("/api/tasks"), req, Task.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Task body = created.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getTitle()).isEqualTo("Write resume");

        // list
        ResponseEntity<Task[]> list = rest.getForEntity(url("/api/tasks"), Task[].class);
        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(list.getBody()).isNotEmpty();

        // update
        Task updated = new Task();
        updated.setTitle("Write resume - updated");
        updated.setCompleted(true);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Task> entity = new HttpEntity<>(updated, headers);
        ResponseEntity<Task> up = rest.exchange(url("/api/tasks/" + body.getId()), HttpMethod.PUT, entity, Task.class);
        assertThat(up.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(up.getBody()).isNotNull();
        assertThat(up.getBody().isCompleted()).isTrue();

        // delete
        ResponseEntity<Void> del = rest.exchange(url("/api/tasks/" + body.getId()), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertThat(del.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }
}
