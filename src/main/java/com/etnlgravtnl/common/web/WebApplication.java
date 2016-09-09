package com.etnlgravtnl.common.web;

import com.etnlgravtnl.common.exception.MapperSupport.ExceptionMapperSupport;
import com.etnlgravtnl.common.filter.AuthorizationRequestFilter;
import com.etnlgravtnl.common.filter.FeatureAnnotatedFactory;
import com.etnlgravtnl.system.interceptor.AirLogFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.server.validation.ValidationConfig;
import org.glassfish.jersey.server.validation.internal.InjectingConstraintValidatorFactory;


import javax.validation.ParameterNameProvider;
import javax.validation.Validation;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guozheng on 2016/6/7.
 */
public class WebApplication extends ResourceConfig{

    public WebApplication() {

        // Validation.
        register(ValidationConfigurationContextResolver.class);
        register(ExceptionMapperSupport.class);
        //注册日志记录
       // register(AirLogFilter.class);
        // MVC.
        register(JspMvcFeature.class);
        //注册注解工厂
        register(FeatureAnnotatedFactory.class);
        //注册身份验证
        register(AuthorizationRequestFilter.class);

    }
    public static class ValidationConfigurationContextResolver implements ContextResolver<ValidationConfig> {

        @Context
        private ResourceContext resourceContext;

        @Override
        public ValidationConfig getContext(final Class<?> type) {
            return new ValidationConfig()
                    .constraintValidatorFactory(resourceContext.getResource(InjectingConstraintValidatorFactory.class))
                    .parameterNameProvider(new CustomParameterNameProvider());
        }

        /**
         * See ContactCardTest#testAddInvalidContact.
         */
        private class CustomParameterNameProvider implements ParameterNameProvider {

            private final ParameterNameProvider nameProvider;

            public CustomParameterNameProvider() {
                nameProvider = Validation.byDefaultProvider().configure().getDefaultParameterNameProvider();
            }

            @Override
            public List<String> getParameterNames(final Constructor<?> constructor) {
                return nameProvider.getParameterNames(constructor);
            }

            @Override
            public List<String> getParameterNames(final Method method) {
                // See ContactCardTest#testAddInvalidContact.
                if ("addContact".equals(method.getName())) {
                    return Arrays.asList("contact");
                }
                return nameProvider.getParameterNames(method);
            }
        }
    }
}
