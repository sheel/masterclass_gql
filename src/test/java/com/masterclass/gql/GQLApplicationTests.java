package com.masterclass.gql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterclass.gql.controller.GQLController;
import com.masterclass.gql.service.GQLService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GQLController.class)
public class GQLApplicationTests
{

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GQLService mockService;

    @Autowired
    ObjectMapper objectMapper;

	@Test
	public void contextLoads()
	{
	}

    @Test
    public void rootContextIsLive() throws Exception {

	    given(mockService.execute("")).willReturn(new ArrayList<>());

        mockMvc.perform(post("/graphql/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes("")))
                .andExpect(status().isOk());
    }
}
