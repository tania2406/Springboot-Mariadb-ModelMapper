package com.example.Modelmapper_example;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ModelMapperConfiguration {

	@Bean
	ModelMapper mymodelmapper()
	{
		return new ModelMapper();
	}
}
