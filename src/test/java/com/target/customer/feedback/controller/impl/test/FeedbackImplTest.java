package com.target.customer.feedback.controller.impl.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.target.customer.feedback.controller.impl.FeedbackImpl;
import com.target.customer.feedback.service.model.CommentMessageValidator;
import com.target.customer.feedback.service.model.FeedbackRequest;
import com.target.customer.feedback.service.model.FeedbackResponse;

import javax.validation.ConstraintValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FeedbackImplTest {

    private static final String FEEDBACK_URL = "/customer/feedback";

    @Autowired
    FeedbackImpl feedbackImpl;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper mapperSpy;

    private MockMvc mockMvc;

    @Autowired
    CommentMessageValidator commentMessageValidator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        final LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setApplicationContext(wac);

        final ConstraintValidatorFactory constraintValidatorFactory = new SpringConstraintValidatorFactory(
                wac.getAutowireCapableBeanFactory());
        constraintValidatorFactory.getInstance(CommentMessageValidator.class);

        validatorFactoryBean.setConstraintValidatorFactory(constraintValidatorFactory);

        this.mockMvc = MockMvcBuilders.standaloneSetup(feedbackImpl).setValidator(validatorFactoryBean).build();
    }

    @Test
    public void testWithQuestionableContent() throws Exception {
        final FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setMessage("It's a Bad product");
        final Gson gson = new Gson();
        final String jsonInString = gson.toJson(feedbackRequest);
        this.mockMvc.perform(MockMvcRequestBuilders.post(FEEDBACK_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonInString).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is4xxClientError());
    }

    @Test
    public void testWithPProperComment() throws Exception {
        final FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setMessage("It's a Good product");
        final Gson gson = new Gson();
        final String jsonInString = gson.toJson(feedbackRequest);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(FEEDBACK_URL).accept(MediaType.APPLICATION_JSON_VALUE).content(jsonInString)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(new ResultHandler() {
                    @Override
                    public void handle(MvcResult result) throws Exception {
                        final FeedbackResponse feedbackResponse = mapperSpy.readValue(result.getResponse().getContentAsString(),
                                FeedbackResponse.class);
                        assertNotNull(feedbackResponse);
                        assertEquals("comment is posted successfully", feedbackResponse.getStatus());
                    }
                });

    }

}
