package br.com.audsat.vehicleinsurancews.useful;

import br.com.audsat.vehicleinsurancews.dto.TokenDTO;
import br.com.audsat.vehicleinsurancews.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Component
public class Utility {

    private final String token;
    private final AuthService authService;

    public Utility(AuthService authService) {
        this.authService = authService;
        this.token = getAcessToken();
    }

    private String getAcessToken() {
        authService.signup(PojoFactory.createValidUser());
        TokenDTO tokenDTO = authService.login(PojoFactory.createValidUser());
        return "Bearer " + tokenDTO.acessToken();
    }

    public String structureOkGetStatus200(String url, MockMvc mockMvc) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        return result.getResponse().getContentAsString();
    }

    public String structureCreatedPostStatus201(String url, Object body, MockMvc mockMvc) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(body);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(url).header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andReturn();
        return result.getResponse().getContentAsString();
    }

    public String structureOkPutStatus200(String url, Object body, MockMvc mockMvc) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(body);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put(url).header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        return result.getResponse().getContentAsString();
    }

    public boolean structureDeleteStatus204(String url, MockMvc mockMvc) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .delete(url).contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().is(204))
                .andReturn();
        return result.getResponse().getContentAsString() == null;
    }

}
