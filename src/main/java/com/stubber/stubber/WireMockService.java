package com.stubber.stubber;

import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.github.tomakehurst.wiremock.client.WireMock;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class WireMockService {

    private final WireMockServer wireMockServer;

    public WireMockService(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }


    public void addMapping() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:3002/__admin/mappings", String.class);
        System.out.println(response);
    }

    @PostConstruct
    public void init() throws IOException {
        WireMock.configureFor("localhost", 3002);
        File stubsDirectory = new File("F:\\repos\\stubber\\stubber\\src\\main\\resources\\WiremockStubsMappings\\DemoUsecase\\demo_response.json");

        if (stubsDirectory.isDirectory()) {
            for (File file : Objects.requireNonNull(stubsDirectory.listFiles())) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    // Read the JSON stub content from the file
                    String jsonStub = FileUtils.readFileToString(file, "UTF-8");

                    // Send a POST request to add the stub
                    WireMock.stubFor(WireMock.post("/__admin/mappings/new")
                            .withRequestBody(WireMock.equalToJson(jsonStub))
                            .willReturn(WireMock.aResponse().withStatus(201))
                    );
                }
            }
        }
    }
}