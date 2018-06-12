package com.expertsoft.web.config;

import com.expertsoft.core.CoreApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.MessageSourceAccessor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Configuration
@EnableCaching
@Import(CoreApplication.class)
public class WebApplicationConfig {

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public NumberFormat priceFormatter() {
        NumberFormat formatter = new DecimalFormat("$#,###.##", DecimalFormatSymbols.getInstance(getLocale()));
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter;
    }
}
