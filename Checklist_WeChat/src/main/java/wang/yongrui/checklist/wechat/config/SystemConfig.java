/**
 * 
 */
package wang.yongrui.checklist.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import wang.yongrui.checklist.wechat.ChecklistWeChatApplication;

/**
 * @author Ryan Wang
 *
 */
@Configuration
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
	 *
	 */
	@EnableSwagger2
	class Swagger2Config {
		@Bean
		public Docket swaggerAPI() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage(ChecklistWeChatApplication.class.getPackage().getName()))
					.paths(PathSelectors.any()).build();
		}
	}
}
