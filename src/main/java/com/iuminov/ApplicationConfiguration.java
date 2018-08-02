package com.iuminov;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.iuminov")
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfiguration extends WebMvcConfigurerAdapter implements WebApplicationInitializer {

    @Autowired
    private Environment env;

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public DataSource dataSource(Environment env) {
        DriverManagerDataSource dm = new DriverManagerDataSource();

        dm.setUrl(env.getProperty("jdbc.url"));
        dm.setDriverClassName(env.getProperty("jdbc.driver"));
        dm.setUsername(env.getProperty("jdbc.username"));
        dm.setPassword(env.getProperty("jdbc.password"));

        return dm;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setPackagesToScan("com.iuminov.model");
        sfb.setDataSource(dataSource);
        return sfb;
    }

    /*@Bean
    public TransactionManager transactionManager() {

    }*/

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(ApplicationConfiguration.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory);
        return tm;
    }

    @Bean
    public JavaMailSender mailSender(Environment env) {

        JavaMailSenderImpl ms = new JavaMailSenderImpl();
        ms.setHost(env.getProperty("email.host"));
        ms.setPort(Integer.valueOf(env.getProperty("email.port")));

        ms.setUsername(env.getProperty("email.username"));
        ms.setPassword(env.getProperty("email.password"));
        Properties props = ms.getJavaMailProperties();
        props.put("mail.transport.protocol", env.getProperty("email.protocol"));
        props.put("mail.smtp.auth", env.getProperty("email.auth"));
        props.put("mail.smtp.starttls.enable", env.getProperty("email.tls"));
        props.put("mail.debug", env.getProperty("email.debug"));
        return ms;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}
