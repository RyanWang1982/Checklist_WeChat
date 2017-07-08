/**
 *
 */
package wang.yongrui.checklist.wechat.config;

import static wang.yongrui.checklist.wechat.constant.SystemConstant.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidatorFactory;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import wang.yongrui.checklist.wechat.ChecklistWeChatApplication;

/**
 * @author Ryan Wang
 *
 */
@Configuration
@EnableSwagger2
public class SystemConfig {

	/**
	 *
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * @return
	 */
	@Bean
	public Docket swaggerAPI() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(ChecklistWeChatApplication.class.getPackage().getName()))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	/**
	 * @return
	 */
	private ApiInfo apiInfo() {
		Contact author = new Contact("Ryan Wang", "www.yongrui.wang", "w_y_r@live.com");
		return new ApiInfoBuilder().contact(author).title("Check List WeChat Mini Program API")
				.description("This API is for Check List WeChat Mini Program").version("0.1").build();
	}

	/**
	 * @return
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor.setValidatorFactory(validator());
		return methodValidationPostProcessor;
	}

	/**
	 * @return
	 */
	@Bean
	public ValidatorFactory validator() {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource());
		return validatorFactoryBean;
	}

	/**
	 * @return
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setBasenames("i18n/text/TextMessages", "i18n/validation/ValidationMessages",
				"i18n/error/ErrorMessages");
		return messageSource;
	}

	/**
	 * @return
	 */
	@Bean
	public HandlerExceptionResolver globalExceptionResolver() {
		return new HandlerExceptionResolver() {

			/*
			 * (non-Javadoc)
			 *
			 * @see org.springframework.web.servlet.HandlerExceptionResolver#
			 * resolveException(javax.servlet.http.HttpServletRequest,
			 * javax.servlet.http.HttpServletResponse, java.lang.Object,
			 * java.lang.Exception)
			 */
			@Override
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception ex) {
				if (ex instanceof ConstraintViolationException) {
					ConstraintViolationException causeException = (ConstraintViolationException) ex;
					for (ConstraintViolation<?> details : causeException.getConstraintViolations()) {
						request.getSession().setAttribute(GLOBAL_ERROR_MESSAGE, details.getMessage());
						break;
					}
				}

				return null;
			}

		};

	}

	/**
	 * @return
	 */
	@Bean
	public DefaultErrorAttributes defaultErrorAttributes() {
		return new DefaultErrorAttributes() {

			/*
			 * (non-Javadoc)
			 *
			 * @see
			 * org.springframework.boot.autoconfigure.web.DefaultErrorAttributes
			 * #getErrorAttributes(org.springframework.web.context.request.
			 * RequestAttributes, boolean)
			 */
			@Override
			public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
					boolean includeStackTrace) {
				Object globalErrorMessage = requestAttributes.getAttribute(GLOBAL_ERROR_MESSAGE,
						RequestAttributes.SCOPE_SESSION);
				Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
				if ("No message available".equals(errorAttributes.get("message")) && null != globalErrorMessage) {
					errorAttributes.put("message", globalErrorMessage);
					requestAttributes.setAttribute(GLOBAL_ERROR_MESSAGE, null, RequestAttributes.SCOPE_SESSION);
				}

				return errorAttributes;
			}

		};
	}

}
