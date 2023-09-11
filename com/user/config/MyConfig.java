package com.user.config;

import com.user.config.interceptor.RestTemplateInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

/*Sure! Imagine you're making a recipe for cooking a dish. You might have a list of ingredients and steps to follow.
In the same way, in a Spring application, you have various pieces of code that create
 and set up things you need (like objects or services).

When you mark a class with `@Configuration`, you're telling Spring,
"Hey, this class has some special instructions on how to create and set up
things." Just like a recipe, this class provides the directions for creating certain
parts of your application. When the application starts, Spring looks at these directions and
 sets up the things you've specified, so they're ready to use when you need them.*/
@Configuration
public class MyConfig {
    private ClientRegistrationRepository clientRegistrationRepository;
    private OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestTemplateInterceptor(manager(
                clientRegistrationRepository,
                auth2AuthorizedClientRepository
        )));

        restTemplate.setInterceptors(interceptors);

        return  restTemplate;
    }

//    @Bean
//    public Retrofit retrofit(){
//        return  Retrofit.Builder();
//    }

    //declare the bean of authorzied Clieint manager
    @Bean
    public OAuth2AuthorizedClientManager manager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
    ){
        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
        defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
        return defaultOAuth2AuthorizedClientManager;


    }


}
