package com.clonecodingproject.clone_coding_project_9_teams.webConfig;

import com.clonecodingproject.clone_coding_project_9_teams.interceptor.JwtTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "test","http://mung.shop.s3-website.ap-northeast-2.amazonaws.com")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///home/ubuntu/upload/");
    }

    //토큰을 받아야 하는 서비스 설정
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(jwtTokenInterceptor) //로그인이 필요한 서비스 요청시 Interceptor가 그 요청을 가로챔
                .addPathPatterns("/test");
    }
}
