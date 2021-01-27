package com.heb.pm;

import com.heb.pm.util.JmsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * Common JMS configuration for the test environment.
 *
 * @author d116773
 * @since 1.30.0
 */
@Configuration
public class TestJmsTemplateFactory {

	private static final Logger logger = LoggerFactory.getLogger(TestJmsTemplateFactory.class);

	@Value("${jsm.product.outboundQueueName}")
	private transient String productPublicationTopic;

	/**
	 * Creates a JmsTempalte to dispatch product messages.
	 *
	 * @param cachingConnectionFactory The JmsConnection too use to make the connection.
	 * @return A JmsTemplate to dispatch messages to the product topic.
	 */
	@Bean(name = "productPublicationTopic")
	public JmsTemplate productPublisherJmsTemplate(ConnectionFactory cachingConnectionFactory) {

		logger.info(String.format("Creating JmsTemplate for the product topic: %s.", this.productPublicationTopic));
		return JmsHelper.newJmsTemplate(cachingConnectionFactory, this.productPublicationTopic, true);
	}
}
