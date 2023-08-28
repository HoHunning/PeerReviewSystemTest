package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import freemarker.template.TemplateException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);
		assert(args.length == 1);
		try{
			TestCaseCreator.createTestCases(Integer.parseInt(args[0]));
		}catch(IOException | TemplateException e){
			System.out.println(e);
		}
	}

}
