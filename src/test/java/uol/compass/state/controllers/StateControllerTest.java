package uol.compass.state.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uol.compass.state.dto.request.StateRequestDTO;
import uol.compass.state.dto.response.StateResponseDTO;
import uol.compass.state.dto.response.StateResponseParameters;
import uol.compass.state.enums.Region;
import uol.compass.state.services.StateServiceImpl;
import uol.compass.state.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = StateController.class)
class StateControllerTest {

    public static final String BASE_URL = "/api/states";
    public static final String ID_URL = BASE_URL + "/1";
    public static final Long ID = 1L;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StateServiceImpl stateService;

    @Test
    void create() throws Exception {
        StateRequestDTO request = getStateRequestDTO();
        StateResponseDTO stateResponseDTO = new StateResponseDTO();

        when(stateService.create(any())).thenReturn(stateResponseDTO);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        StateRequestDTO request = getStateRequestDTO();
        StateResponseDTO stateResponseDTO = new StateResponseDTO();

        when(stateService.update(any(), any())).thenReturn(stateResponseDTO);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void findById() throws Exception {
        StateResponseDTO stateResponseDTO = new StateResponseDTO();

        when(stateService.findById(any())).thenReturn(stateResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findAll() throws Exception {
        StateResponseParameters stateResponseParameters = new StateResponseParameters();

        when(stateService.findAll(any(), any())).thenReturn(stateResponseParameters);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private StateRequestDTO getStateRequestDTO() {
        return StateRequestDTO.builder()
                .name("Test")
                .capital("Capital")
                .region(Region.NORDESTE)
                .area(0.0)
                .population(1)
                .build();
    }
}