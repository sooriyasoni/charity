package charity.charity.controllers;

import charity.charity.data.MemberRepository;
import charity.charity.models.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @MockBean
    MemberRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {

        var request = post("/api/member")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();

        Member member = new Member();
        String agencyJson = jsonMapper.writeValueAsString(member);

        var request = post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(agencyJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());

    }

    @Test
    void addShouldReturn415WhenMultipart() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();

        Member member = new Member(0, "TST", "Test ", "developer", "121221", "soorya.soni@gmail.com", "ha14yw");
        String agencyJson = jsonMapper.writeValueAsString(member);

        var request = post("/api/member")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(agencyJson);

        mvc.perform(request)
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void addShouldReturn201() throws Exception {

        Member member = new Member(0, "TST", "Test ", "developer", "121221", "soorya.soni@gmail.com", "ha14yw");
        Member expected = new Member(0, "TST", "Test ", "developer", "121221", "soorya.soni@gmail.com", "ha14yw");

        when(repository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();

        String memberJson = jsonMapper.writeValueAsString(member);

        var request = post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(memberJson);

        mvc.perform(request)
                .andExpect(status().isCreated());
    }

}
