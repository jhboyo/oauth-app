package com.app.global.config.web;

import com.app.global.interceptor.AdminAuthorizationInterceptor;
import com.app.global.interceptor.AuthenticationInterceptor;
import com.app.global.resolver.memberinfo.MemberInfoArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final MemberInfoArgumentResolver memberInfoArgumentResolver;
    private final AdminAuthorizationInterceptor adminAuthorizationInterceptor;

    public WebConfig(AuthenticationInterceptor authenticationInterceptor, MemberInfoArgumentResolver memberInfoArgumentResolver, AdminAuthorizationInterceptor adminAuthorizationInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
        this.memberInfoArgumentResolver = memberInfoArgumentResolver;
        this.adminAuthorizationInterceptor = adminAuthorizationInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/api/**")
               .allowedOrigins("*")
               .allowedMethods(
                       HttpMethod.GET.name(),
                       HttpMethod.POST.name(),
                       HttpMethod.PUT.name(),
                       HttpMethod.PATCH.name(),
                       HttpMethod.DELETE.name(),
                       HttpMethod.OPTIONS.name()
               );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .order(1)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/oauth/login",
                                    "/api/access-token/issue",
                                    "/api/logout",
                                    "/api/health");
        registry.addInterceptor(adminAuthorizationInterceptor)
                .order(2)
                .addPathPatterns("/api/admin/**");

    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberInfoArgumentResolver);
    }
}
