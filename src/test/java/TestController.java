import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucifer.model.User;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TestController extends BaseTest {

    ObjectMapper objectMapper = new ObjectMapper();
    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }
    @Test
    public void createUser() throws Exception  {
        User user = new User();
        user.setLast("fengxuan");
        user.setFirst("liu");
        user.setEmail("liufeng45gh@163.com");
        user.setPassword("XXXXX");

        String body = objectMapper.writeValueAsString(user);

        super.mockMvc.perform(post("/user")
                .content(body)
//                .param("first", "bbb")
//                .param("last", "bbb")
//                .param("email", "liufeng45gh@163.com")
//                .param("password", "1qaz2wsx")

                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document("createUser",

                        requestFields(
                fieldWithPath("first")
                        .description("first name"),
                fieldWithPath("last").description("last name"),
                fieldWithPath("email").description("邮箱"),
                fieldWithPath("password").description("密码")
                                )));

//                requestPartFields(
//                        fieldWithPath("contact.email").description("The user's email address"),
//                        fieldWithPath("contact.name").description("The user's name"))));
                        //preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),

//                        requestParameters(parameterWithName("email").description("邮箱"),
//                                parameterWithName("password").description("密码"),
//                                parameterWithName("first").description("first name"),
//                                parameterWithName("last").description("last name"))
               // ));
    }

    @Test
    public void loginTest() throws Exception  {

        User user = new User();
        user.setEmail("liufeng45gh@163.com");
        user.setPassword("XXXXX");

        String body = objectMapper.writeValueAsString(user);

        super.mockMvc.perform(post("/user/login")
                .content(body)
//                .param("email", "liufeng45gh@163.com")
//                .param("password", "1qaz2wsx")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document("userLogin",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").description("邮箱"),
                                fieldWithPath("password").description("密码")
                        )));
    }

    @Test
    public void getUser() throws Exception  {
        super.mockMvc.perform(get("/user")
                .header("token", "xxxxxxxxxxxx")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document("getUser",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())
                        //requestParameters(parameterWithName("token").description("登录凭据"))
                ));
    }

    @Test
    public void logout() throws Exception  {
        super.mockMvc.perform(post("/user/logout")
                .header("token", "xxxxxxxxxxxx")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document("logout",
                                requestHeaders(
                                        headerWithName("token").description(
                                                "Basic auth credentials"))
                        //preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())
                        //requestParameters(parameterWithName("token").description("登录凭据"))


                        ));
    }
}

