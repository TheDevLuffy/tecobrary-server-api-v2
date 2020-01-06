package com.woowacourse.tecobrary.common.util;

import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.util.UserJwtDtoMapper;
import com.woowacourse.tecobrary.user.infra.util.JwtUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class AcceptanceTestUtils {

    protected static final String DOCUMENTATION_OUTPUT_DIRECTORY = "{class-name}/{method-name}";

    protected RequestSpecification spec;

    @LocalServerPort
    protected int port;

    @BeforeEach
    void setUp(final RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
    }

    protected String baseUrl() {
        return "http://localhost:" + port;
    }

    protected String baseUrl(final String path) {
        return baseUrl() + path;
    }

    protected String generateAuthToken(final User user) {
        return String.format("Bearer %s", JwtUtils.generateToken(UserJwtDtoMapper.toDto(user)));
    }
}
